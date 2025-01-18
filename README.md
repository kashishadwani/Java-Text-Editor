# Java Text Editor ✍️

A feature-rich text editor built with **Java Swing**, offering a clean and intuitive interface, along with powerful text editing features. This project showcases robust functionality while maintaining simplicity, making it a perfect tool for users and an excellent demonstration of your programming skills.

---

## 📋 Features

### 🔤 **Text Editing**
- Basic text editing capabilities with a `JTextArea`.
- File operations:
  - **New, Open, Save, Save As**.
- Clipboard support:
  - **Cut, Copy, Paste** functionality.
- Tracks modifications to notify about unsaved changes.

### 🎨 **Syntax Highlighting**
- Highlights common programming keywords (e.g., reserved words in blue).
- Extensible framework to add more syntax rules.

### ↩️ **Auto-Indentation**
- Maintains the indentation level of the previous line.
- Automatically handles the **Enter** key for proper alignment.
- Preserves existing indentation patterns.

### 🔍 **Find and Replace**
- Complete **Find/Replace** dialog:
  - **Find**, **Replace**, and **Replace All** options.
  - Case-sensitive search.
  - Highlights found text for better visibility.

### 🧹 **Undo and Redo**
- Fully integrated **Undo/Redo** support using `UndoManager`.
- Tracks all text modifications.
- Accessible via menu options and keyboard shortcuts.

### 💡 **User Interface**
- Clean and intuitive design:
  - **Menu bar** for all operations.
  - **Toolbar** for quick access to common tasks.
  - **File chooser** for seamless open/save functionality.
- Status indicators in the window title (e.g., modified status).
- Confirmation dialogs to handle unsaved changes.

---

## 🚀 Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or above.

### Steps to Run the Editor
1. **Compile the Java source file**:
   ```bash
   javac TextEditor.java

2. **Run the main Method**:
   ```bash
   java TextEditor
