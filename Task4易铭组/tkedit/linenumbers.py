import tkinter as tk

class LineNumbers(tk.Text):
    def __init__(self, master, text_widget, **kwargs):
        super().__init__(master, **kwargs)

        self.text_widget = text_widget
        self.text_widget.bind('<KeyRelease-Return>', self.on_key_press)
        self.text_widget.bind('<KeyRelease-BackSpace>', self.on_key_press)

        self.text_widget.bind('<KeyRelease-Up>', self.update_line_numbers)
        self.text_widget.bind('<KeyRelease-Down>', self.update_line_numbers)
        self.text_widget.bind('<KeyRelease-Prior>', self.update_line_numbers)
        self.text_widget.bind('<KeyRelease-Next>', self.update_line_numbers)

        self.insert(1.0, '1')
        self.configure(state='disabled')

    def on_key_press(self, event=None):
        final_index = str(self.text_widget.index(tk.END))
        num_of_lines = final_index.split('.')[0]
        # Change
        line_numbers_string = "\n".join(str(no) for no in range(1, int(num_of_lines)))

        width = len(str(int(num_of_lines) - 1))

        print("insert:        " + self.text_widget.index("insert"))
        print("current:        " + self.text_widget.index("current"))
        print("end:             " + self.text_widget.index(tk.END))
        # Change

        self.configure(state='normal', width=width)
        self.delete(1.0, tk.END)
        self.insert(1.0, line_numbers_string)
        self.update_line_numbers()
        self.configure(state='disabled')

    def force_update(self):
        self.on_key_press()

    def update_line_numbers(self, event=None):
        self.see(self.text_widget.index("insert"))
