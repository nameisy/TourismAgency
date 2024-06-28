package core;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class Helper {
    public static void setTheme() {

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    public static void showMsg(String str) {
        optionPayneTR();
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Please fill in all fields !";
                title = "Hata!";
            }
            case "done" -> {
                msg = "Operation Successful !";
                title = "Conclusion";
            }
            case "notFound" -> {
                msg = "No record found !";
                title = "Not found";
            }
            case "error" -> {
                msg = "You have made an incorrect operation !";
                title = "Error!";
            }
            default -> {
                msg = str;
                title = "Message";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        optionPayneTR();
        String msg;
        if (str.equals("sure")) {
            msg = "Are you sure you want to do this operation? !!";

        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Are you sure?", JOptionPane.YES_NO_OPTION) == 0;

    }

    public static void optionPayneTR() {
        UIManager.put("OptionPane.okButtonText", "Ok");
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "No");

    }

    //For room properties texts for radios, see the Add a room screen.
    public static String roomProperty(String number){
        String property="";
        switch (number){
            case "1":
                property = "Television ";
                break;
            case "2":
                property = "Minibar ";
                break;
            case "3":
                property = "Game Console";
                break;
            case "4":
                property = "Safe deposit box";
                break;
            case "5":
                property = "Projection";
                break;
        }
        return property;
    }
    public static boolean isValidDate(String inputDate, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        try {
            LocalDate date = LocalDate.parse(inputDate, formatter);
            return true; // Current date format
        } catch (DateTimeParseException e) {
            return false; // Invalid date format
        }
    }
    public static LocalDate parseDate(String inputDate, String formatPattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);

        try {
            LocalDate date = LocalDate.parse(inputDate, formatter);
            return date; // Successful conversion
        } catch (DateTimeParseException e) {
            return null; // Conversion failed
        }
    }

    public static List<String> getListFromJList(JList<String> jList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jList.getModel().getSize(); i++) {
            list.add(jList.getModel().getElementAt(i));
        }
        return list;
    }
    // Method to convert object list to String list
    public static List<String> convertObjectListToStringList(Object[] objectList) {
        List<String> stringList = new ArrayList<>();
        for (Object person : objectList) {
            // Convert each object to String and add it to the list
            String str = person.toString(); // toString method is called
            stringList.add(str);
        }
        return stringList;
    }
    public static boolean isList_J_Empty(JList<?> list) {
        ListModel<?> model = list.getModel();
        return model.getSize() == 0;
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();

    }
    public static boolean isAreaEmpty(JTextArea field) {
        return field.getText().trim().isEmpty();
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }
}
