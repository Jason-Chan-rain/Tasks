# Tkedit

### 一、源代码分析

1. 原工程GUI使用的Tkinter，网上查找资料发现Tkinter的更新与维护并不很好，上次更新在十年之前，开发者也大都对Tkinter的评价不是很好，不如pyQt和wxpython
2. 程序bug（yaml用法变更）：`yaml.load(stream)`—>`yaml.load(stream, Loader=yaml.FullLoader)`![yaml](C:\Users\Dell\Desktop\Task4\image\yaml.png)
3. 原工程文件多且关系不明了，代码易读性差

### 二、需求分析

1. 基本功能：文件（新建、打开、保存、另存为），编辑（剪切、删除、复制、粘贴、查找、替换），工具（主题、字体）
2. 扩展功能：行数统计、字数统计
3. UI设计：操作方便、界面简洁
4. 其他功能：打印、缩放

### 三、软件设计

1. Tkinter：Tkinter 模块(Tk 接口)是 Python 的标准 Tk GUI 工具包的接口 .Tk 和 Tkinter 可以在大多数的 Unix 平台下使用,同样可以应用在 Windows 和 Macintosh 系统里。Tk8.0 的后续版本可以实现本地窗口风格,并良好地运行在绝大多数平台中。python内置的GUI框架，使用TCL实现，python中内嵌了TCL解释器，使用它的时候不用安装额外的扩展包，直接import，跨平台。不足之处在于UI布局全靠代码实现，只有15种常用部件，显示效果简陋。 

   pyQt：QT原本是诺基亚的产品，源码用C++写的，python对QT的包装，跨平台，本地显示效果，根据系统决定，在win7下就是win7的显示效果；pyqt与qt的函数接口一致，qt开发问的那个丰富，所以pyqt开发文档也比较丰富；控件丰富，函数/方法多，拖曳布局；方便打包成二进制文件；GPL协议，商业程序需要购买商业版授权。

2. 原工程使用的是Tkinter，但是发现有更好的选择即pyQt，相比于前者，使用pyQt可以用更少的代码实现更美观的界面。pyQt的库对于记事本的基本功能有着更好的支持，因此我使用pyQt模仿windows记事本实现了一个简单的文本编辑软件。

3. 基本功能的实现：

```python
self.plainTextEdit.undo()#撤销
self.plainTextEdit.cut()#剪切
self.plainTextEdit.copy()#复制
self.plainTextEdit.paste()#粘贴
self.plainTextEdit.textCursor().deletePreviousChar()#删除
self.plainTextEdit.selectAll()#全选
```

4. 查找与替换

```python
    def find(self):
        self.fintText = self.lineEditFind.text()
        if self.fintText == "":
            QtWidgets.QMessageBox.information(self, "Tkedit", "请输入搜索内容")
            return False
        if self.checkBoxCase.isChecked() == True and self.radioButtonFindProv.isChecked() == True:
            result = self.plainTextEdit.find(self.fintText,
QtGui.QTextDocument.FindCaseSensitively | QtGui.QTextDocument.FindBackward)
        elif self.checkBoxCase.isChecked() == True and self.radioButtonFindNext.isChecked() == True:
            result = self.plainTextEdit.find(self.fintText, QtGui.QTextDocument.FindCaseSensitively)
        elif self.checkBoxCase.isChecked() == False and self.radioButtonFindProv.isChecked() == True:
            result = self.plainTextEdit.find(self.fintText, QtGui.QTextDocument.FindBackward)
        else:
            result = self.plainTextEdit.find(self.fintText)
        if result == False:
            QtWidgets.QMessageBox.information(self, "Tkedit", "找不到\"" + self.fintText + "\"")
        return result
    
     def replace(self, All=False):
        if self.find() == False:
            return False
        self.replaceText = self.lineEditReplace.text()
        if self.replaceText == "":
            QtWidgets.QMessageBox.information(self, "Tkedit", "请输入替换内容")
            return False
        if All == True:
            result = self.plainTextEdit.toPlainText().replace(self.fintText, self.replaceText)
            self.plainTextEdit.setPlainText(result)
        else:
            self.plainTextEdit.cut()
            self.plainTextEdit.insertPlainText(self.replaceText)
```

5. 文件操作

```python
    def newFile(self):
        self.openFilePath = ''
        self.openFileName = '无标题.txt'
        self.setWindowTitle(self.openFileName + ' - Tkedit')
        self.plainTextEdit.clear()

    def openFile(self):
        filename, filetype = QtWidgets.QFileDialog.getOpenFileName(self, "打开", "./", "文本文档 (*.txt);;所有文件 (*)")
        if filename != "":
            self.plainTextEdit.clear()
            with open(filename, 'r', encoding='gb18030', errors='ignore') as f:
                self.plainTextEdit.appendPlainText(f.read())
            f.close()
            self.openFilePath = filename
            self.openFileName = os.path.basename(filename)
            self.setWindowTitle(self.openFileName + ' - Tkedit')

    def saveFile(self):
        if self.openFilePath == "":
            filename, filetype = QtWidgets.QFileDialog.getSaveFileName(self, '保存', './', "文本文档 (*.txt);;所有文件 (*)")
            if filename == "":
                return False

            self.openFilePath = filename
            self.openFileName = os.path.basename(filename)
            self.setWindowTitle(self.openFileName + ' - Tkedit')

        file = open(self.openFilePath, 'w', encoding='gb18030', errors='ignore')
        file.write(self.plainTextEdit.toPlainText())
        file.close()
        self.setWindowTitle(self.openFileName + ' - Tkedit')
        self.isSaved = True
        return True

    def saveas(self):
        filename, filetype = QtWidgets.QFileDialog.getSaveFileName(self, '保存', './', "文本文档 (*.txt);;所有文件 (*)")
        if filename == "":
            return False

        self.openFilePath = filename
        self.openFileName = os.path.basename(filename)
        self.setWindowTitle(self.openFileName + ' - Tkedit')
        file = open(self.openFilePath, 'w', encoding='gb18030', errors='ignore')
        file.write(self.plainTextEdit.toPlainText())
        file.close()
        self.setWindowTitle(self.openFileName + ' - Tkedit')
        self.isSaved = True
        return True
```

6. 行列数与字数统计

```python
row = self.plainTextEdit.textCursor().blockNumber()
        col = self.plainTextEdit.textCursor().columnNumber()
        comp= self.plainTextEdit.toPlainText().replace("\n","").replace(" ","").strip()
        length = len(comp)
        self.statusBar.showMessage("行 %d , 列 %d，字数 %d" % (row + 1, col + 1, length ))
```

![word](C:\Users\Dell\Desktop\Task4\image\word.png)

7. UI

![UI](C:\Users\Dell\Desktop\Task4\image\UI.png)

![task4](C:\Users\Dell\Desktop\Task4\image\task4.gif)

