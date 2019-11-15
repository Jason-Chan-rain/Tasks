import tkinter as tk
import os
import subprocess


class Terminal(tk.Text):
    def __init__(self, master, **kwargs):
        super().__init__(**kwargs)

        self.master = master

        self.config(wrap=tk.WORD)  # WORD CHAR NONE

        self.tag_configure('find_match', background="black")
        self.find_match_index = None
        self.find_search_starting_index = 1.0

        # TODO
        # autom = r"Python D:\Downloads\Chapter11\Ch11\tkedit\test.py"
        # powershell = r"C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe"
        # self.file = subprocess.Popen("%s %s" % (powershell, autom,))

        self.bind_events()
        self.hint()
        self.mark_set("prev", self.index(str(tk.END) + "-2c"))

    def bind_events(self):
        self.bind('<Control-a>', self.select_all)
        self.bind('<Control-c>', self.copy)
        self.bind('<Control-v>', self.paste)
        self.bind('<Control-x>', self.cut)
        self.bind('<Control-y>', self.redo)
        self.bind('<Control-z>', self.undo)
        self.bind('<KeyRelease-Return>', self.input_string)
        self.bind('<KeyPress>', self.set_insert)
        self.bind('<BackSpace>', self.set_delete)

    # Change
    def set_insert(self, event=None):
        print("Insert:  " + self.index("insert"))
        print("Prev:    " + self.index("prev"))
        edit_mark = self.index(str(self.index("prev")) + "+1c")
        if self.compare(self.index("insert"), '<', edit_mark):
            print("true")
            self.mark_set("insert", edit_mark)
        print("Insert:  " + self.index("insert"))
        print("Prev:    " + self.index("prev"))
    # Change
    # Change
    def set_delete(self, event=None):
        print("Insert:  " + self.index("insert"))
        print("Prev:    " + self.index("prev"))
        edit_mark = self.index(str(self.index("prev")) + "+1c")
        if self.compare(self.index("insert"), '<=', edit_mark):
            print("true")
            return "break"
        elif self.compare(self.index("sel.first"), '<=', edit_mark):
            print("Sel.f:  " + self.index("sel.first"))
            print("Sel.l:  " + self.index("sel.last"))
            return "break"

    # Change

    def cut(self, event=None):
        self.event_generate("<<Cut>>")

        return "break"

    def copy(self, event=None):
        self.event_generate("<<Copy>>")

        return "break"

    def paste(self, event=None):
        self.event_generate("<<Paste>>")

        return "break"

    def undo(self, event=None):
        self.event_generate("<<Undo>>")

        return "break"

    def redo(self, event=None):
        self.event_generate("<<Redo>>")

        return "break"

    def select_all(self, event=None):
        self.tag_add("sel", 1.0, tk.END)

        return "break"

    def input_string(self, event=None):
        line = str(self.index("insert"))
        line_start = line + " linestart"
        idx_start = self.index(str(self.index("prev")) + "+1c")
        idx_end = self.search('\n', line_start, tk.END)
        string = self.get(self.index(idx_start), self.index(idx_end))
        print(string)
        self.display_file_contents(string)

    def display_file_contents(self, contents):
        print(contents)
        with os.popen(contents, "r") as file:
            # self.delete(1.0, tk.END)
            output = file.read()
            file.close()
            print(output)
            self.insert(tk.END, output)
        self.hint()
        self.mark_set("prev", self.index(tk.END + "-2c"))

        # TODO
        # output = self.file.stdout.readlines(hint="")
        # for line in output:
        #     print(line)
        # self.file.wait()

    def hint(self):
        self.insert(tk.END, "PC: " + os.getcwd() + "> ")

# Python D:\Downloads\Chapter11\Ch11\tkedit\test.py