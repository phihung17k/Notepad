package GUI;

import com.ozten.font.JFontChooser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;



public class MyNotepad extends javax.swing.JFrame {
    JFileChooser fc=new JFileChooser();
    String untitle="Untitled - Notepad";
    // key của hm là filename, value chứa get(0) là path, get(1) là content
    HashMap<String, ArrayList<String>> hm=new HashMap<>();
    String textSelected="";
    
    public MyNotepad() {
        MyNotepad mn = this;
        initComponents();
        this.setTitle(untitle);
        fc.addChoosableFileFilter(new MyFileFilter("Text Document(*.txt)", ".txt"));
        txtContent.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e){
                int pos=0, lineNumber=0, lineStart=0, column=0;
                try {
                    pos=txtContent.getCaretPosition();
                    lineNumber=txtContent.getLineOfOffset(pos);
                    lineStart=txtContent.getLineStartOffset(lineNumber);
                    column=pos-lineStart;
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
                lbStatusBar.setText("Ln "+(lineNumber+1)+", Col "+(column+1));
            }
        });
        cbStatusBar.setSelected(true);
        lbStatusBar.setVisible(true);
        this.addWindowListener(new WindowAdapter() { // cài đặt WindowAdpater vì không cần implements toàn bộ phương thức của interface Listener
            public void windowClosing(WindowEvent e)
            {
                String content=mn.txtContent.getText();
                if (mn.getTitle().equals(untitle) || mn.getTitle().equals("*" + untitle)) {
                    if (!content.isEmpty()) {
                        int a = saveChanges("Untitled");
                        switch (a) {
                            case JOptionPane.YES_OPTION:
                                saveFileExist(content);
                            case JOptionPane.NO_OPTION:
                                mn.dispose();
                                break;
                        }
                    }
                } else {
                    String filename = mn.getTitle();
                    File f = new File(hm.get(filename).get(0));
                    if (hm.get(filename).get(1).equals(content)) {
                        mn.dispose();
                    } else {
                        int a = saveChanges(f.getPath());
                        switch (a) {
                            case JOptionPane.YES_OPTION:
                                writeTextToFile(content, f);
                            case JOptionPane.NO_OPTION:
                                mn.dispose();
                                break;
                        }
                    }
                }
            }
        });
    }
    public boolean checkFileText(File f) {
        if (!f.getName().matches("\\w+.txt"))
            return false;
        return true;
    }
    public void checkFileExit(File f, String text)
    {
        if (f.exists()) {
            int ans = JOptionPane.showConfirmDialog(null, f.getName() + " already existes.\nDo you want to replace it?", "Notepad",
                    JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) {
                writeTextToFile(text, f);//ghi đè
                JOptionPane.showMessageDialog(null, "Successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Canceled!");
            }
        } else {
            writeTextToFile(text, f);
            JOptionPane.showMessageDialog(null, "Successfully!");
        }
    }
    public String readTextFromFile(File file) 
    {
        String result="";
        FileReader f=null;
        BufferedReader bf=null;
        try {
            f=new FileReader(file);
            bf=new BufferedReader(f);
            while(bf.ready())
            {
                String s=bf.readLine();
                result+=s+"\n";
            }
            result=result.substring(0, result.length()-1);
            ArrayList<String> al=new ArrayList<>();
            al.add(file.getPath());
            al.add(result);
            hm.put(file.getName(), al);
            this.setTitle(file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(f!=null) f.close();
                if(bf!=null) bf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public void writeTextToFile(String text, File file)
    {
        PrintWriter w=null;
        try {
            w=new PrintWriter(file);
            w.print(text);
            ArrayList<String> al=new ArrayList<>();
            al.add(file.getPath());
            al.add(text);
            hm.put(file.getName(), al);
            this.setTitle(file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(w!=null) w.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public int saveChanges(String title) {
        String[] option = {"Save", "Don't Save", "Cancel"};
        int answer = JOptionPane.showOptionDialog(null, "Do you want to save changes to " + title + "?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        return answer;
    }

    public void saveFileExist(String text) {
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            if (checkFileText(f)) {
                checkFileExit(f, text);
            }
            else{
                JOptionPane.showMessageDialog(null, "The extension should be .txt");
                saveFileExist(text);
            }
        }
    }
    
    public void openFileExist()
    {
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            txtContent.setText(readTextFromFile(f));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        mnNew2 = new javax.swing.JMenuItem();
        mnOpen2 = new javax.swing.JMenuItem();
        mnSave2 = new javax.swing.JMenuItem();
        mnSaveAs2 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mnUndo2 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mnCut2 = new javax.swing.JMenuItem();
        mnCopy2 = new javax.swing.JMenuItem();
        mnPaste2 = new javax.swing.JMenuItem();
        mnDelete2 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        lbStatusBar = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        mnNew = new javax.swing.JMenuItem();
        mnOpen = new javax.swing.JMenuItem();
        mnSave = new javax.swing.JMenuItem();
        mnSaveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnExit = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenu();
        mnUndo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnCut = new javax.swing.JMenuItem();
        mnCopy = new javax.swing.JMenuItem();
        mnPaste = new javax.swing.JMenuItem();
        mnDelete = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnFind = new javax.swing.JMenuItem();
        mnReplace = new javax.swing.JMenuItem();
        mnGoTo = new javax.swing.JMenuItem();
        Format = new javax.swing.JMenu();
        mnFont = new javax.swing.JMenuItem();
        View = new javax.swing.JMenu();
        cbStatusBar = new javax.swing.JCheckBoxMenuItem();

        mnNew2.setText("New");
        mnNew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNew2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnNew2);

        mnOpen2.setText("Open");
        mnOpen2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpen2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnOpen2);

        mnSave2.setText("Save");
        mnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSave2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnSave2);

        mnSaveAs2.setText("Save As");
        mnSaveAs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSaveAs2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnSaveAs2);
        popupMenu.add(jSeparator5);

        mnUndo2.setText("Undo");
        mnUndo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnUndo2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnUndo2);
        popupMenu.add(jSeparator4);

        mnCut2.setText("Cut");
        mnCut2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCut2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnCut2);

        mnCopy2.setText("Copy");
        mnCopy2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCopy2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnCopy2);

        mnPaste2.setText("Paste");
        mnPaste2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPaste2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnPaste2);

        mnDelete2.setText("Delete");
        mnDelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDelete2ActionPerformed(evt);
            }
        });
        popupMenu.add(mnDelete2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtContent.setColumns(20);
        txtContent.setRows(5);
        txtContent.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtContentCaretUpdate(evt);
            }
        });
        txtContent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtContentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtContent);

        lbStatusBar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbStatusBar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStatusBar.setText("Ln 1, Col 1");

        File.setText("File");

        mnNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mnNew.setText("New");
        mnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNewActionPerformed(evt);
            }
        });
        File.add(mnNew);

        mnOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnOpen.setText("Open...");
        mnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenActionPerformed(evt);
            }
        });
        File.add(mnOpen);

        mnSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnSave.setText("Save");
        mnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSaveActionPerformed(evt);
            }
        });
        File.add(mnSave);

        mnSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnSaveAs.setText("Save As...");
        mnSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSaveAsActionPerformed(evt);
            }
        });
        File.add(mnSaveAs);
        File.add(jSeparator1);

        mnExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mnExit.setText("Exit");
        mnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnExitActionPerformed(evt);
            }
        });
        File.add(mnExit);

        jMenuBar1.add(File);

        Edit.setText("Edit");

        mnUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        mnUndo.setText("Undo");
        mnUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnUndoActionPerformed(evt);
            }
        });
        Edit.add(mnUndo);
        Edit.add(jSeparator2);

        mnCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mnCut.setText("Cut");
        mnCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCutActionPerformed(evt);
            }
        });
        Edit.add(mnCut);

        mnCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnCopy.setText("Copy");
        mnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCopyActionPerformed(evt);
            }
        });
        Edit.add(mnCopy);

        mnPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mnPaste.setText("Paste");
        mnPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPasteActionPerformed(evt);
            }
        });
        Edit.add(mnPaste);

        mnDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        mnDelete.setText("Delete");
        mnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDeleteActionPerformed(evt);
            }
        });
        Edit.add(mnDelete);
        Edit.add(jSeparator3);

        mnFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        mnFind.setText("Find");
        mnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFindActionPerformed(evt);
            }
        });
        Edit.add(mnFind);

        mnReplace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mnReplace.setText("Replace...");
        mnReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnReplaceActionPerformed(evt);
            }
        });
        Edit.add(mnReplace);

        mnGoTo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        mnGoTo.setText("Go To...");
        mnGoTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGoToActionPerformed(evt);
            }
        });
        Edit.add(mnGoTo);

        jMenuBar1.add(Edit);

        Format.setText("Format");

        mnFont.setText("Font");
        mnFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFontActionPerformed(evt);
            }
        });
        Format.add(mnFont);

        jMenuBar1.add(Format);

        View.setText("View");

        cbStatusBar.setSelected(true);
        cbStatusBar.setText("Status Bar");
        cbStatusBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStatusBarActionPerformed(evt);
            }
        });
        View.add(cbStatusBar);

        jMenuBar1.add(View);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
            .addComponent(lbStatusBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lbStatusBar)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtContentCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtContentCaretUpdate
        if(this.getTitle().equals(untitle)|| this.getTitle().equals("*"+untitle))
        {
            this.setTitle("*"+untitle);
        }
    }//GEN-LAST:event_txtContentCaretUpdate

    private void mnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSaveActionPerformed
        String content=txtContent.getText();
        if(this.getTitle().equals(untitle) || this.getTitle().equals("*"+untitle))
            saveFileExist(content);
        else{
            String filename=this.getTitle();
            File f=new File(hm.get(filename).get(0));
            writeTextToFile(content, f);
        }
    }//GEN-LAST:event_mnSaveActionPerformed

    private void mnSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSaveAsActionPerformed
        String content=txtContent.getText();
        saveFileExist(content);
    }//GEN-LAST:event_mnSaveAsActionPerformed

    private void mnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNewActionPerformed
        String content=txtContent.getText();
        if(this.getTitle().equals(untitle) || this.getTitle().equals("*"+untitle))
        {
            if(!content.isEmpty())
            {
                int a=saveChanges("Untitled");
                switch(a)
                {
                    case JOptionPane.YES_OPTION:
                        saveFileExist(content);
                    case JOptionPane.NO_OPTION:
                        txtContent.setText("");
                        this.setTitle(untitle);
                        break;
                }
            }
        }
        else{
            String filename= this.getTitle();
            File f=new File(hm.get(filename).get(0));
            if(hm.get(filename).get(1).equals(content))
            {
                this.setTitle(untitle);
                txtContent.setText("");
            }
            else{
                int a=saveChanges(f.getPath());
                switch(a)
                {
                    case JOptionPane.YES_OPTION:
                        writeTextToFile(content, f);
                    case JOptionPane.NO_OPTION:
                        txtContent.setText("");
                        this.setTitle(untitle);
                        break;
                }
            }
        }
    }//GEN-LAST:event_mnNewActionPerformed

    private void mnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenActionPerformed
    String content=txtContent.getText();
    if(this.getTitle().equals(untitle) || this.getTitle().equals("*"+untitle))
        {
            if(!content.isEmpty())
            {
                int ans=saveChanges(untitle);
                switch(ans)
                {
                    case JOptionPane.YES_OPTION: saveFileExist(content);
                    case JOptionPane.NO_OPTION:
                            openFileExist();
                        break;
                }
            }
            else{
                    openFileExist();
            }
        }
        else{
            String filename=this.getTitle();
            File fi=new File(hm.get(filename).get(0));
            if(content.equals(hm.get(filename).get(1)))
                openFileExist();
            else{
                int ans=saveChanges(fi.getPath());
                switch (ans) {
                    case JOptionPane.YES_OPTION:
                        writeTextToFile(content, fi);
                    case JOptionPane.NO_OPTION:
                        openFileExist();
                        break;
                }
            }
        }
    }//GEN-LAST:event_mnOpenActionPerformed

    private void mnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnExitActionPerformed
        String content=txtContent.getText();
        if(this.getTitle().equals(untitle) || this.getTitle().equals("*"+untitle))
        {
            if(!content.isEmpty())
            {
                int ans=saveChanges("Untitled");
                if(ans==JOptionPane.YES_OPTION)
                {
                    saveFileExist(content);
                }
                else if(ans==JOptionPane.NO_OPTION)
                    this.dispose();
            }
            else{
                this.dispose();
            }
        }
        else{
            String filename=this.getTitle();
            File f=new File(hm.get(filename).get(0));
            if(content.equals(hm.get(filename).get(1)))
            {
                this.dispose();
            }
            else{
                int ans=saveChanges(f.getPath());
                if(ans==JOptionPane.YES_OPTION)
                {
                    writeTextToFile(content, f);
                }
                this.dispose();
            }
        }
    }//GEN-LAST:event_mnExitActionPerformed

    private void mnUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnUndoActionPerformed
        UndoManager undoManager = new UndoManager();
        txtContent.setLineWrap(true);//text auto xuống dòng khi vượt quá kích thước của textArea
        txtContent.setWrapStyleWord(true);//xuống dòng tại vị trí whitespace 
        //add 1 bộ lắng nghe chỉnh sửa để cập nhật các bản ghi thay đổi 
        txtContent.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });
        mnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });
    }//GEN-LAST:event_mnUndoActionPerformed

    private void mnCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCutActionPerformed
        try{
        textSelected=txtContent.getSelectedText();
        if(!textSelected.equals("") && textSelected!=null)
            txtContent.replaceSelection("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Nothing is selected");
        }
    }//GEN-LAST:event_mnCutActionPerformed

    private void mnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCopyActionPerformed
        textSelected=txtContent.getSelectedText();
        if(textSelected.equals("") || textSelected==null)
            JOptionPane.showMessageDialog(this, "Nothing is selected");
    }//GEN-LAST:event_mnCopyActionPerformed

    private void mnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPasteActionPerformed
        try {
            if (!textSelected.equals("") && textSelected != null) {
                int i = txtContent.getCaretPosition();
                txtContent.insert(textSelected, i);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Can't find any copy or cut string");
        }
    }//GEN-LAST:event_mnPasteActionPerformed

    private void mnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDeleteActionPerformed
        txtContent.replaceSelection("");
    }//GEN-LAST:event_mnDeleteActionPerformed

    private void mnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFindActionPerformed
        Find find=new Find(this, true);
        find.setVisible(true);
    }//GEN-LAST:event_mnFindActionPerformed

    private void mnReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnReplaceActionPerformed
        Replace rp=new Replace(this, false);
        rp.setVisible(true);
    }//GEN-LAST:event_mnReplaceActionPerformed

    private void mnGoToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGoToActionPerformed
        GoTo goTo=new GoTo(this,true);
        goTo.setVisible(true);
    }//GEN-LAST:event_mnGoToActionPerformed

    private void cbStatusBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusBarActionPerformed
        if(cbStatusBar.isSelected()==false)
            lbStatusBar.setVisible(false);
        else
            lbStatusBar.setVisible(true);
    }//GEN-LAST:event_cbStatusBarActionPerformed

    private void mnFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFontActionPerformed
        Font mf=JFontChooser.showDialog(this, "choose your font: ");
        if(mf!=null) txtContent.setFont(mf);
    }//GEN-LAST:event_mnFontActionPerformed

    private void txtContentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtContentMouseClicked
        int x=evt.getXOnScreen();
        int y=evt.getYOnScreen();
        if(evt.getButton()==3)//chuot phai
            popupMenu.show(null, x, y);
        else
            popupMenu.setVisible(false);
    }//GEN-LAST:event_txtContentMouseClicked

    private void mnNew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNew2ActionPerformed
        popupMenu.setVisible(false);
        mnNewActionPerformed(evt);
    }//GEN-LAST:event_mnNew2ActionPerformed

    private void mnOpen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpen2ActionPerformed
        popupMenu.setVisible(false);
        mnOpenActionPerformed(evt);
    }//GEN-LAST:event_mnOpen2ActionPerformed

    private void mnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSave2ActionPerformed
        popupMenu.setVisible(false);
        mnSaveActionPerformed(evt);
    }//GEN-LAST:event_mnSave2ActionPerformed

    private void mnSaveAs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSaveAs2ActionPerformed
        popupMenu.setVisible(false);
        mnSaveAsActionPerformed(evt);
    }//GEN-LAST:event_mnSaveAs2ActionPerformed

    private void mnUndo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnUndo2ActionPerformed
        popupMenu.setVisible(false);
        mnUndoActionPerformed(evt);
    }//GEN-LAST:event_mnUndo2ActionPerformed

    private void mnCut2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCut2ActionPerformed
        popupMenu.setVisible(false);
        mnCutActionPerformed(evt);
    }//GEN-LAST:event_mnCut2ActionPerformed

    private void mnCopy2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCopy2ActionPerformed
        popupMenu.setVisible(false);
        mnCopyActionPerformed(evt);
    }//GEN-LAST:event_mnCopy2ActionPerformed

    private void mnDelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDelete2ActionPerformed
        popupMenu.setVisible(false);
        mnDeleteActionPerformed(evt);
    }//GEN-LAST:event_mnDelete2ActionPerformed

    private void mnPaste2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPaste2ActionPerformed
        popupMenu.setVisible(false);
        mnPasteActionPerformed(evt);
    }//GEN-LAST:event_mnPaste2ActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyNotepad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Edit;
    private javax.swing.JMenu File;
    private javax.swing.JMenu Format;
    private javax.swing.JMenu View;
    private javax.swing.JCheckBoxMenuItem cbStatusBar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JLabel lbStatusBar;
    private javax.swing.JMenuItem mnCopy;
    private javax.swing.JMenuItem mnCopy2;
    private javax.swing.JMenuItem mnCut;
    private javax.swing.JMenuItem mnCut2;
    private javax.swing.JMenuItem mnDelete;
    private javax.swing.JMenuItem mnDelete2;
    private javax.swing.JMenuItem mnExit;
    private javax.swing.JMenuItem mnFind;
    private javax.swing.JMenuItem mnFont;
    private javax.swing.JMenuItem mnGoTo;
    private javax.swing.JMenuItem mnNew;
    private javax.swing.JMenuItem mnNew2;
    private javax.swing.JMenuItem mnOpen;
    private javax.swing.JMenuItem mnOpen2;
    private javax.swing.JMenuItem mnPaste;
    private javax.swing.JMenuItem mnPaste2;
    private javax.swing.JMenuItem mnReplace;
    private javax.swing.JMenuItem mnSave;
    private javax.swing.JMenuItem mnSave2;
    private javax.swing.JMenuItem mnSaveAs;
    private javax.swing.JMenuItem mnSaveAs2;
    private javax.swing.JMenuItem mnUndo;
    private javax.swing.JMenuItem mnUndo2;
    private javax.swing.JPopupMenu popupMenu;
    public javax.swing.JTextArea txtContent;
    // End of variables declaration//GEN-END:variables
}
