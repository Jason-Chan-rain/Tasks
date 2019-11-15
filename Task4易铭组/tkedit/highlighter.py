import tkinter as tk

import yaml


class Highlighter:
    def __init__(self, text_widget, syntax_file):
        self.text_widget = text_widget
        self.syntax_file = syntax_file
        self.categories = None
        self.numbers_colour = "blue"
        self.strings_colour = "red"

        self.disallowed_previous_chars = ["_", "-", "."]

        self.parse_syntax_file()

        self.text_widget.bind('<KeyRelease>', self.on_key_release)

    #Change
    def on_key_release(self, event=None):
        self.force_highlight()
    #Change

    def parse_syntax_file(self):
        with open(self.syntax_file, 'r') as stream:
            try:
                config = yaml.load(stream)
            except yaml.YAMLError as error:
                print(error)
                return

        self.categories = config['categories']
        self.numbers_colour = config['numbers']['colour']
        self.strings_colour = config['strings']['colour']

        self.configure_tags()

    def configure_tags(self):
        for category in self.categories.keys():
            colour = self.categories[category]['colour']
            self.text_widget.tag_configure(category, foreground=colour)

        self.text_widget.tag_configure("number", foreground=self.numbers_colour)
        self.text_widget.tag_configure("string", foreground=self.strings_colour)

    # Change
    def highlight_tags(self, event=None):
        for category in self.categories:
            matches = self.categories[category]['matches']
            start = 1.0
            idx = self.text_widget.tag_nextrange(category, start, tk.END)
            while (idx):
                word_end_index = self.text_widget.index(str(idx[0]) + " wordend")
                tag_keyword = self.text_widget.get(idx[0], word_end_index)
                # tag_keyword = self.text_widget.get(idx[0], idx[1])
                print(tag_keyword)
                print(idx[0], idx[1])
                if tag_keyword not in matches:
                    self.text_widget.tag_remove(category, idx[0], idx[1])
                    print("Remove:" + tag_keyword + " " + str(tag_keyword not in category))
                idx = self.text_widget.tag_nextrange(category, word_end_index, tk.END)
    # Change

    # Change
    def highlight(self, event=None):
        length = tk.IntVar()
        for category in self.categories:
            matches = self.categories[category]['matches']
            for keyword in matches:
                start = 1.0
                keyword = keyword  # + "[^A-Za-z_-]"
                idx = self.text_widget.search(keyword, start, stopindex=tk.END, count=length, regexp=1)
                while idx:
                    char_match_found = int(str(idx).split('.')[1])
                    line_match_found = int(str(idx).split('.')[0])

                    word_end_index = self.text_widget.index(str(idx) + " wordend")
                    word_length = int(str(word_end_index).split('.')[1]) - char_match_found

                    print("Idx:" + idx)
                    print("WordEnd:" + word_end_index)
                    print("WordLength:" + str(word_length))

                    if char_match_found > 0:
                        previous_char_index = str(line_match_found) + '.' + str(char_match_found - 1)
                        previous_char = self.text_widget.get(previous_char_index, previous_char_index + "+1c")

                        if previous_char.isalnum() or previous_char in self.disallowed_previous_chars or word_length != length.get():
                            end = f"{idx}+{length.get()}c"
                            start = end
                            idx = self.text_widget.search(keyword, start, stopindex=tk.END, regexp=1)
                        else:
                            end = f"{idx}+{length.get()}c"
                            self.text_widget.tag_add(category, idx, end)

                            start = end
                            idx = self.text_widget.search(keyword, start, stopindex=tk.END, regexp=1)
                    else:
                        if word_length != length.get():
                            end = f"{idx}+{length.get()}c"
                            start = end
                            idx = self.text_widget.search(keyword, start, stopindex=tk.END, regexp=1)
                        else:
                            end = f"{idx}+{length.get()}c"
                            self.text_widget.tag_add(category, idx, end)

                            start = end
                            idx = self.text_widget.search(keyword, start, stopindex=tk.END, regexp=1)

        self.highlight_regex(r"(\d)+[.]?(\d)*", "number")
        self.highlight_regex(r"[\'][^\']*[\']", "string")
        self.highlight_regex(r"[\"][^\']*[\"]", "string")
    # Change

    def highlight_regex(self, regex, tag):
        length = tk.IntVar()
        start = 1.0
        idx = self.text_widget.search(regex, start, stopindex=tk.END, regexp=1, count=length)
        while idx:
            end = f"{idx}+{length.get()}c"
            self.text_widget.tag_add(tag, idx, end)

            start = end
            idx = self.text_widget.search(regex, start, stopindex=tk.END, regexp=1, count=length)

    def force_highlight(self):
        self.highlight_tags()
        self.highlight()

    def clear_highlight(self):
        for category in self.categories:
            self.text_widget.tag_remove(category, 1.0, tk.END)


if __name__ == '__main__':
    w = tk.Tk()
    h = Highlighter(tk.Text(w), 'languages/python.yaml')
    w.mainloop()
