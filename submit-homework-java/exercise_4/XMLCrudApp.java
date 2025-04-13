package bai_tap_crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLCrudApp extends JFrame {
    private JTextField tagNameField, tagValueField, childTagNameField, childTagValueField;
    private DefaultListModel<String> xmlListModel;
    private JList<String> xmlList;
    private ArrayList<Element> xmlElements = new ArrayList<>();
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;

    public XMLCrudApp() {
        setTitle("XML CRUD Java GUI");
        setSize(600, 600);  // Increased size to accommodate more input fields
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Tên thẻ:"));
        tagNameField = new JTextField();
        inputPanel.add(tagNameField);

        inputPanel.add(new JLabel("Giá trị:"));
        tagValueField = new JTextField();
        inputPanel.add(tagValueField);

        inputPanel.add(new JLabel("Tên thẻ con:"));
        childTagNameField = new JTextField();
        inputPanel.add(childTagNameField);

        inputPanel.add(new JLabel("Giá trị thẻ con:"));
        childTagValueField = new JTextField();
        inputPanel.add(childTagValueField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton addButton = new JButton("Thêm thẻ");
        JButton deleteButton = new JButton("Xóa thẻ");
        JButton updateButton = new JButton("Cập nhật thẻ");
        JButton saveButton = new JButton("Ghép & Lưu XML");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(saveButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Danh sách thẻ
        xmlListModel = new DefaultListModel<>();
        xmlList = new JList<>(xmlListModel);
        JScrollPane scrollPane = new JScrollPane(xmlList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách thẻ XML"));
        add(scrollPane, BorderLayout.CENTER);

        // Hành động thêm thẻ
        addButton.addActionListener(e -> {
            String tagName = tagNameField.getText().trim();
            String tagValue = tagValueField.getText().trim();
            String childTagName = childTagNameField.getText().trim();
            String childTagValue = childTagValueField.getText().trim();

            if (!tagName.isEmpty()) {
                Element element = doc.createElement(tagName);
                element.setTextContent(tagValue);

                // Thêm thẻ con nếu có
                if (!childTagName.isEmpty()) {
                    Element childElement = doc.createElement(childTagName);
                    childElement.setTextContent(childTagValue);
                    element.appendChild(childElement);
                }

                xmlElements.add(element);

                // Cập nhật danh sách hiển thị
                String displayText = "<" + tagName + ">" + tagValue + "</" + tagName + ">";
                if (!childTagName.isEmpty()) {
                    displayText += " (Con: <" + childTagName + ">" + childTagValue + "</" + childTagName + ">)";
                }
                xmlListModel.addElement(displayText);

                // Xóa nội dung trường nhập
                tagNameField.setText("");
                tagValueField.setText("");
                childTagNameField.setText("");
                childTagValueField.setText("");
            }
        });

        // Hành động xóa thẻ
        deleteButton.addActionListener(e -> {
            int selectedIndex = xmlList.getSelectedIndex();
            if (selectedIndex != -1) {
                xmlElements.remove(selectedIndex);
                xmlListModel.remove(selectedIndex);
            }
        });

        // Hành động cập nhật thẻ
        updateButton.addActionListener(e -> {
            int selectedIndex = xmlList.getSelectedIndex();
            String newTagName = tagNameField.getText().trim();
            String newTagValue = tagValueField.getText().trim();
            String newChildTagName = childTagNameField.getText().trim();
            String newChildTagValue = childTagValueField.getText().trim();

            if (selectedIndex != -1 && !newTagName.isEmpty()) {
                Element updatedElement = doc.createElement(newTagName);
                updatedElement.setTextContent(newTagValue);

                // Cập nhật thẻ con nếu có
                if (!newChildTagName.isEmpty()) {
                    Element childElement = doc.createElement(newChildTagName);
                    childElement.setTextContent(newChildTagValue);
                    updatedElement.appendChild(childElement);
                }

                xmlElements.set(selectedIndex, updatedElement);

                // Cập nhật danh sách hiển thị
                String displayText = "<" + newTagName + ">" + newTagValue + "</" + newTagName + ">";
                if (!newChildTagName.isEmpty()) {
                    displayText += " (Con: <" + newChildTagName + ">" + newChildTagValue + "</" + newChildTagName + ">)";
                }
                xmlListModel.set(selectedIndex, displayText);

                // Xóa nội dung trường nhập
                tagNameField.setText("");
                tagValueField.setText("");
                childTagNameField.setText("");
                childTagValueField.setText("");
            }
        });

        // Hành động ghép và lưu XML
        saveButton.addActionListener(e -> {
            if (xmlElements.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Danh sách rỗng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Document newDoc = dBuilder.newDocument();
                Element root = newDoc.createElement("root");
                newDoc.appendChild(root);

                for (Element el : xmlElements) {
                    Element imported = (Element) newDoc.importNode(el, true);
                    root.appendChild(imported);
                }

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Lưu file XML");
                int userSelection = fileChooser.showSaveDialog(this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    DOMSource source = new DOMSource(newDoc);
                    StreamResult result = new StreamResult(fileToSave);
                    transformer.transform(source, result);
                    JOptionPane.showMessageDialog(this, "Lưu thành công tại: " + fileToSave.getAbsolutePath());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu file XML!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new XMLCrudApp().setVisible(true);
        });
    }
}
