import tkinter as tk
import tkinter.ttk as ttk
from tkinter.font import families


class FontChooser(tk.Toplevel):
    def __init__(self, master, **kwargs):
        super().__init__(**kwargs)
        self.master = master

        self.transient(self.master)
        self.geometry('350x250')
        self.title('Choose font and size')
        self.configure(bg=self.master.background)

        self.text_to_font = tk.StringVar()
        self.text_to_size = tk.StringVar()

        top_frame = tk.Frame(self, bg=self.master.background)
        middle_frame = tk.Frame(self, bg=self.master.background)
        bottom_frame = tk.Frame(self, bg=self.master.background)
        font_entry_label = ttk.Label(top_frame, text="Font: ", style="editor.TLabel")
        self.font_comb = ttk.Combobox(top_frame,width=20,textvariable=self.text_to_font)
        self.text_to_font.set(self.master.font_family)
        size_entry_label = ttk.Label(middle_frame, text="Size: ", style="editor.TLabel")
        self.size_input = tk.Spinbox(middle_frame, from_=0, to=99, textvariable=self.text_to_size)
        self.text_to_size.set(self.master.font_size)


        self.save_button = ttk.Button(bottom_frame, text="Save", command=self.save, style="editor.TButton")

        font_entry_label.pack(side=tk.LEFT)
        size_entry_label.pack(side=tk.LEFT)

        self.size_input.pack(side=tk.LEFT, fill=tk.X, expand=1)
        self.font_comb.pack(side=tk.LEFT, fill=tk.X, expand=1)

        self.save_button.pack(side=tk.BOTTOM, pady=(0, 20))
        top_frame.pack(side=tk.TOP, expand=1, fill=tk.X, padx=30)
        middle_frame.pack(side=tk.TOP, expand=1, fill=tk.X, padx=30)
        bottom_frame.pack(side=tk.TOP, expand=1, fill=tk.X)


        self.available_fonts = sorted(families())
        self.font_comb['values'] = self.available_fonts


    def save(self):
        font_family = self.font_comb.get()
        yaml_file_contents = f"family: {font_family}\n" \
                             + f"size: {self.size_input.get()}"

        with open(self.master.font_scheme_path, 'w') as file:
            file.write(yaml_file_contents)

        self.master.update_font()

