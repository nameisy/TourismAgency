package view;

import business.HotelManager;
import business.PropertyManager;
import business.SeasonManager;
import business.TypeManager;
import core.Helper;
import entity.Hotel;
import entity.Property;
import entity.Season;
import entity.Types;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HotelAddView extends Layout {
    private JPanel container;
    private JList<String> lst_left_pro;
    private JList<String> lst_right_pro;
    private JList<String> lst_left_type;
    private JList<String> lst_right_type;
    private JButton btn_single_right;
    private JButton btn_multi_right;
    private JButton btn_multi_left;
    private JButton btn_single_left;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_district;
    private JTextField fld_hoteml_fllAdres;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JComboBox<Integer> cmb_hotel_star;
    private JButton btn_hotel_save;
    private JButton btn_single_right_type;
    private JButton btn_multi_right_type;
    private JButton btn_single_left_type;
    private JButton btn_multi_left_type;
    private JRadioButton rd_btn_sum;
    private JRadioButton rd_btn_win;
    private JFormattedTextField fld_season_start;
    private JFormattedTextField fld_season_end;
    private JPanel pnl_hotel;
    private JPanel pntl_top;
    private JPanel pnl_season;
    private JPanel pnl_property;
    private JPanel pnl_type;

    private DefaultListModel<String> leftListPropertyModel;
    private DefaultListModel<String> rightListPropertyModel;

    private DefaultListModel<String> leftListTypeModel;
    private DefaultListModel<String> rightListTypeModel;

    private HotelManager hotelManager;
    private Hotel hotel;
    private PropertyManager propertyManager;
    private Property property;
    private TypeManager typeManager;
    private Types types;
    private SeasonManager seasonManager;
    private Season season;

    List<String> listRightType;

    // HotelManegementGUI constructor method.
    public HotelAddView(Hotel hotel,Property property, Types types ,Season season) {
        this.hotelManager = new HotelManager();
        this.hotel = hotel;
        this.propertyManager = new PropertyManager();
        this.property = property;
        this.typeManager = new TypeManager();
        this.types = types;
        this.seasonManager = new SeasonManager();
        this.season = season;
        add(container);
        this.guiInitilaze(700, 650);
        rightListTypeModel = new DefaultListModel<>();
        rightListPropertyModel = new DefaultListModel<>();

        Integer[] integers = {1, 2, 3, 4, 5};
        // Create a DefaultComboBoxModel and add integers to the model.
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>(integers);
        cmb_hotel_star.setModel(model);

        // Creating GUI components.
        hotelPropertyListComponent();
        hotelTypeListComponent();




        // Adding an ActionListener to the save button.
        btn_hotel_save.addActionListener(e -> {
            boolean isProper = false;
            boolean isType = false;
            boolean isSeason = false;

            // Create a new Property object and set the propertyNames list.

            JTextField[] checkFieldList = {
                    this.fld_hotel_name,
                    this.fld_hotel_city,
                    this.fld_hotel_district,
                    this.fld_hoteml_fllAdres,
                    this.fld_hotel_mail,
                    this.fld_hotel_phone,
                    this.fld_season_start,
                    this.fld_season_end,
            };
            if (Helper.isFieldListEmpty(checkFieldList) ||
                    Helper.isList_J_Empty(lst_right_pro) ||
                    Helper.isList_J_Empty(lst_right_type) ||
                    (!rd_btn_sum.isSelected() || !rd_btn_win.isSelected())){
                Helper.showMsg("Mandatory Sections Missing!");
            }
            else if (!Helper.isValidDate(fld_season_start.getText(),("dd-mm-yyyy")) || !Helper.isValidDate(fld_season_end.getText(),("dd-mm-yyyy"))) {
                Helper.showMsg("Invalid date entered!");
            }else{
                //this.hotel.setHotel_id(tempHotelID);
                this.hotel.setHotel_name(fld_hotel_name.getText());
                this.hotel.setHotel_city(fld_hotel_city.getText());
                this.hotel.setHotel_district(fld_hotel_district.getText());
                this.hotel.setHotel_fllAdres(fld_hoteml_fllAdres.getText());
                this.hotel.setHotel_email(fld_hotel_mail.getText());
                this.hotel.setHotel_phone(fld_hotel_phone.getText());
                this.hotel.setHotel_star((Integer)cmb_hotel_star.getSelectedItem());


                List<Season> seasons = new ArrayList<>();
                Season summerSeason = new Season();
                summerSeason.setSeasonName("Summer");
                summerSeason.setSeason_start(LocalDate.parse(fld_season_start.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                summerSeason.setSeason_end(LocalDate.parse(fld_season_end.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                seasons.add(summerSeason);

//Create the Winter season and add it to the list
                Season winterSeason = new Season();
                winterSeason.setSeasonName("Winter");
                winterSeason.setSeason_start(LocalDate.parse(fld_season_start.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                winterSeason.setSeason_end(LocalDate.parse(fld_season_end.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                seasons.add(winterSeason);


                int hotelId  = this.hotelManager.saveAndGetHotelId(this.hotel);

                        if(hotelId !=0) {
                            List<String> propertyNames = Helper.getListFromJList(lst_right_pro);
                            this.property.setPropertyNames(propertyNames);
                            isProper = propertyManager.save(this.property,hotelId);


                            List<String> listRightType = Helper.getListFromJList(lst_right_type);
                            isType = this.typeManager.save(hotelId, listRightType);


                            isSeason = this.seasonManager.save(seasons, hotelId);

                            if (isProper) {
                                Helper.showMsg("Feature recording is successful.");
                            } else {
                                Helper.showMsg("Speciality registration failed.");
                            }
                            if (isType) {
                                Helper.showMsg("Type recording successful.");
                            } else {
                                Helper.showMsg("Type registration failed.");
                            }
                            if (isSeason) {
                                Helper.showMsg("Season recording successful.");
                            } else {
                                Helper.showMsg("Season registration failed.");
                            }
                            Helper.showMsg("Hotel information recording successful.");
                        }else {
                            Helper.showMsg("Hotel fails to record information.");
                        }
                     }
        });

    }
    public void hotelPropertyListComponent() {
        leftListPropertyModel = new DefaultListModel<>();
        String[] propertyArray = {"Free Car Parking",
                "Free WiFi",
                "Swimming Pool",
                "Fitness Centre",
                "Hotel Concierge",
                "SPA",
                "24/7 Room Service"};

        for (int i = 0; i < propertyArray.length; i++) {
            leftListPropertyModel.addElement(propertyArray[i]);
        }
        lst_left_pro.setModel(leftListPropertyModel);

        btn_single_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getLeftListValue = lst_left_pro.getSelectedValue();
                int value = lst_left_pro.getSelectedIndex();
                // getLeftListValue != null
                if (value >= 0 && !rightListPropertyModel.contains(getLeftListValue)) {
                    rightListPropertyModel.addElement(getLeftListValue);
                    lst_right_pro.setModel(rightListPropertyModel);
                } else if (getLeftListValue == null) {
                    Helper.showMsg("Özellik Seçiniz");
                } else {
                    Helper.showMsg("Listede Mevcut");
                }
            }
        });



        btn_single_left.addActionListener(e -> {
            if (lst_right_pro.getSelectedValue() != null) {

                rightListPropertyModel.removeElement(lst_right_pro.getSelectedValue());

            } else {
                Helper.showMsg("Özellik Seçiniz");
            }
        });
        btn_multi_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftListPropertyModel.getSize() == rightListPropertyModel.getSize()) {
                    Helper.showMsg("Listenin tümü mevcut");
                } else {
                    // Get all items in the left list.
                    List<String> allItems = new ArrayList<>();
                    for (int i = 0; i < leftListPropertyModel.getSize(); i++) {
                        allItems.add(leftListPropertyModel.getElementAt(i));
                    }
                    // Get the current items in the right list into a List.
                    List<String> existingItems = new ArrayList<>();
                    for (int i = 0; i < rightListPropertyModel.getSize(); i++) {
                        existingItems.add(rightListPropertyModel.getElementAt(i));

                    }
                    // Add all items in the left list to the right list.
                    for (String item : allItems) {
                        if (!existingItems.contains(item)) {
                            rightListPropertyModel.addElement(item);
                            lst_right_pro.setModel(rightListPropertyModel);

                        }
                    }
                }
            }
        });
        btn_multi_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightListPropertyModel.removeAllElements();

            }
        });
    }
    public void hotelTypeListComponent() {

        leftListTypeModel = new DefaultListModel<>();

        String[] typeArray = {
                "Ultra All Inclusive",
                "All Inclusive",
                "Bed and Breakfast",
                "Full Board",
                "Half Board",
                "Just the bed",
                "Alcohol Excluded",
        };

        for (int i = 0; i < typeArray.length; i++) {
            leftListTypeModel.addElement(typeArray[i]);
        }
        lst_left_type.setModel(leftListTypeModel);

        btn_single_right_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getLeftListValue = lst_left_type.getSelectedValue();
                int value = lst_left_type.getSelectedIndex();
                // getLeftListValue != null
                if (value >= 0 && !rightListTypeModel.contains(getLeftListValue)) {
                    rightListTypeModel.addElement(getLeftListValue);
                    lst_right_type.setModel(rightListTypeModel);
                } else if (getLeftListValue == null) {
                    Helper.showMsg("Available on the List");
                } else {
                    Helper.showMsg("Available on the List!");
                }
            }
        });
        btn_single_left_type.addActionListener(e -> {
            // String getLeftListValue = lst_right.getSelectedValue();
            //int value=lst_right.getSelectedIndex();
            if (lst_right_type.getSelectedValue() != null) {
                rightListTypeModel.removeElement(lst_right_type.getSelectedValue());
            } else {
                Helper.showMsg("Select Feature.");
            }
        });
        btn_multi_right_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftListTypeModel.getSize() == rightListTypeModel.getSize()) {
                    Helper.showMsg("The complete list is available.");
                } else {
                    // Get all items in the left list.
                    List<String> allItems = new ArrayList<>();
                    for (int i = 0; i < leftListTypeModel.getSize(); i++) {
                        allItems.add(leftListTypeModel.getElementAt(i));
                    }

                    // Get the current items in the right list into a List.
                    List<String> existingItems = new ArrayList<>();
                    for (int i = 0; i < rightListTypeModel.getSize(); i++) {
                        existingItems.add(rightListTypeModel.getElementAt(i));
                    }

                    // Add all items in the left list to the right list.
                    for (String item : allItems) {
                        if (!existingItems.contains(item)) {
                            rightListTypeModel.addElement(item);
                            lst_right_type.setModel(rightListTypeModel);
                        }
                    }
                }


            }
        });
        btn_multi_left_type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rightListTypeModel.removeAllElements();
            }
        });
    }




}
