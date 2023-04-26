import javax.swing.;
import java.awt.;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ReservationSystem extends JFrame implements ActionListener {
private JComboBox<String> cityComboBox;
private JButton viewFacilitiesBtn, makeReservationBtn, cancelReservationBtn, modifyReservationBtn;
private JTextField reservationIdTxtField, guestsTxtField;
private JList<String> facilitiesList;
private JCheckBox tennisCheckBox, basketballCheckBox, footballCheckBox;
private JSpinner dateSpinner, timeSpinner;
private JTextArea facilityDetailsTextArea;
private JButton reserveBtn, confirmBtn, cancelBtn;
private JTextField nameTxtField, emailTxtField;

private ArrayList<Booking> bookings = new ArrayList<Booking>();

public ReservationSystem() {
    setTitle("Sports Facility Reservation System");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);

    JPanel mainPanel = new JPanel(new BorderLayout());

    JPanel topPanel = new JPanel(new FlowLayout());
    JLabel cityLabel = new JLabel("City:");
    cityComboBox = new JComboBox<String>(new String[]{"City A", "City B", "City C"});
    viewFacilitiesBtn = new JButton("View Facilities");
    viewFacilitiesBtn.addActionListener(this);
    topPanel.add(cityLabel);
    topPanel.add(cityComboBox);
    topPanel.add(viewFacilitiesBtn);

    JPanel centerPanel = new JPanel(new GridLayout(1, 2));
    JPanel facilitiesPanel = new JPanel(new BorderLayout());
    JLabel facilitiesLabel = new JLabel("Available Facilities:");
    facilitiesList = new JList<String>(new String[]{"Facility 1", "Facility 2", "Facility 3"});
    facilitiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane facilitiesScrollPane = new JScrollPane(facilitiesList);
    facilitiesPanel.add(facilitiesLabel, BorderLayout.NORTH);
    facilitiesPanel.add(facilitiesScrollPane, BorderLayout.CENTER);

    JPanel filtersPanel = new JPanel(new GridLayout(0, 1));
    JPanel sportFiltersPanel = new JPanel(new FlowLayout());
    JLabel sportFiltersLabel = new JLabel("Sport Filters:");
    tennisCheckBox = new JCheckBox("Tennis");
    basketballCheckBox = new JCheckBox("Basketball");
    footballCheckBox = new JCheckBox("Football");
    sportFiltersPanel.add(sportFiltersLabel);
    sportFiltersPanel.add(tennisCheckBox);
    sportFiltersPanel.add(basketballCheckBox);
    sportFiltersPanel.add(footballCheckBox);
    filtersPanel.add(sportFiltersPanel);

    JPanel capacityPanel = new JPanel(new FlowLayout());
    JLabel guestsLabel = new JLabel("Number of Guests:");
    guestsTxtField = new JTextField(5);
    capacityPanel.add(guestsLabel);
    capacityPanel.add(guestsTxtField);
    filtersPanel.add(capacityPanel);

    JPanel dateTimePanel = new JPanel(new FlowLayout());
    JLabel dateLabel = new JLabel("Date:");
    dateSpinner = new JSpinner(new SpinnerDateModel());
    JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
    dateSpinner.setEditor(dateEditor);
    JLabel timeLabel = new JLabel("Time:");
    timeSpinner = new JSpinner(new SpinnerDateModel());
    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
    timeSpinner.setEditor(timeEditor);
    dateTimePanel.add(dateLabel);
    dateTimePanel.add(dateSpinner);
    dateTimePanel.add(timeLabel);
    dateTimePanel.add(timeSpinner);
    filtersPanel.add(dateTimePanel);

    JPanel buttonsPanel = new JPanel(new FlowLayout());
    makeReservationBtn = new JButton("Make Reservation");
    makeReservationBtn.addActionListener(this);
    cancelReservationBtn = new JButton("Cancel Reservation");
    cancelReservationBtn.addActionListener(this);
    centerPanel.add(facilitiesPanel);
    centerPanel.add(filtersPanel);

JPanel bottomPanel = new JPanel(new BorderLayout());
JPanel reservationPanel = new JPanel(new GridLayout(0, 1));
JLabel reservationLabel = new JLabel("Reservation Details:");
JPanel reservationIdPanel = new JPanel(new FlowLayout());
JLabel reservationIdLabel = new JLabel("Reservation ID:");
reservationIdTxtField = new JTextField(10);
reservationIdPanel.add(reservationIdLabel);
reservationIdPanel.add(reservationIdTxtField);
JPanel facilityDetailsPanel = new JPanel(new BorderLayout());
JLabel facilityDetailsLabel = new JLabel("Facility Details:");
facilityDetailsTextArea = new JTextArea(5, 20);
JScrollPane facilityDetailsScrollPane = new JScrollPane(facilityDetailsTextArea);
facilityDetailsPanel.add(facilityDetailsLabel, BorderLayout.NORTH);
facilityDetailsPanel.add(facilityDetailsScrollPane, BorderLayout.CENTER);
reservationPanel.add(reservationLabel);
reservationPanel.add(reservationIdPanel);
reservationPanel.add(facilityDetailsPanel);

JPanel reservationButtonsPanel = new JPanel(new FlowLayout());
reserveBtn = new JButton("Reserve");
reserveBtn.addActionListener(this);
confirmBtn = new JButton("Confirm Reservation");
confirmBtn.addActionListener(this);
cancelBtn = new JButton("Cancel");
cancelBtn.addActionListener(this);
reservationButtonsPanel.add(reserveBtn);
reservationButtonsPanel.add(confirmBtn);
reservationButtonsPanel.add(cancelBtn);
bottomPanel.add(reservationPanel, BorderLayout.CENTER);
bottomPanel.add(reservationButtonsPanel, BorderLayout.SOUTH);

JPanel rightPanel = new JPanel(new GridLayout(0, 1));
JPanel customerDetailsPanel = new JPanel(new GridLayout(0, 1));
JLabel customerDetailsLabel = new JLabel("Customer Details:");
JPanel namePanel = new JPanel(new FlowLayout());
JLabel nameLabel = new JLabel("Name:");
nameTxtField = new JTextField(10);
namePanel.add(nameLabel);
namePanel.add(nameTxtField);
JPanel emailPanel = new JPanel(new FlowLayout());
JLabel emailLabel = new JLabel("Email:");
emailTxtField = new JTextField(10);
emailPanel.add(emailLabel);
emailPanel.add(emailTxtField);
customerDetailsPanel.add(customerDetailsLabel);
customerDetailsPanel.add(namePanel);
customerDetailsPanel.add(emailPanel);

rightPanel.add(customerDetailsPanel);

mainPanel.add(topPanel, BorderLayout.NORTH);
mainPanel.add(centerPanel, BorderLayout.CENTER);
mainPanel.add(bottomPanel, BorderLayout.SOUTH);
mainPanel.add(rightPanel, BorderLayout.EAST);

add(mainPanel);

setVisible(true);
}

public void actionPerformed(ActionEvent e) {
if (e.getSource() == viewFacilitiesBtn) {
// Retrieve selected city
String selectedCity = (String) cityComboBox.getSelectedItem();
// Retrieve available facilities for selected city
  String[] availableFacilities = retrieveAvailableFacilities(selectedCity);

  // Display available facilities
  facilitiesList.setListData(availableFacilities);

  // Clear facility details area
  facilityDetailsTextArea.setText("");
} else if (e.getSource() == makeReservationBtn) {
  // Retrieve selected facility
  String selectedFacility = facilitiesList.getSelectedValue();

  if (selectedFacility == null) {
    // Display error message if no facility is selected
    JOptionPane.showMessageDialog(null, "Please select a facility.", "Error", JOptionPane.ERROR_MESSAGE);
  } else {
    // Retrieve facility details
    Facility facility = retrieveFacilityDetails(selectedFacility);

    // Display facility details
    facilityDetailsTextArea.setText(facility.toString());
/ Enable reserve button
reserveBtn.setEnabled(true);

// Show reservation panel
cardLayout.show(centerPanel, "reservationPanel");
}

/**

Creates a panel with reservation details for the given booking.

@param booking The booking to display details for.

@return The panel with reservation details.
*/
private JPanel createReservationDetailsPanel(Booking booking) {
JPanel panel = new JPanel(new GridLayout(0, 2));
panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

JLabel nameLabel = new JLabel("Name:");
panel.add(nameLabel);
nameTxtField = new JTextField(booking.getName());
panel.add(nameTxtField);

JLabel emailLabel = new JLabel("Email:");
panel.add(emailLabel);
emailTxtField = new JTextField(booking.getEmail());
panel.add(emailTxtField);

JLabel guestsLabel = new JLabel("Guests:");
panel.add(guestsLabel);
guestsTxtField = new JTextField(Integer.toString(booking.getNumGuests()));
panel.add(guestsTxtField);

JLabel facilityLabel = new JLabel("Facility:");
panel.add(facilityLabel);
JTextField facilityTxtField = new JTextField(booking.getFacilityName());
facilityTxtField.setEditable(false);
panel.add(facilityTxtField);

JLabel dateLabel = new JLabel("Date:");
panel.add(dateLabel);
JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(booking.getDate(), null, null, Calendar.DAY_OF_MONTH));
JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
dateSpinner.setEditor(dateEditor);
panel.add(dateSpinner);

JLabel timeLabel = new JLabel("Time:");
panel.add(timeLabel);
JSpinner timeSpinner = new JSpinner(new SpinnerDateModel(booking.getDate(), null, null, Calendar.HOUR_OF_DAY));
JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
timeSpinner.setEditor(timeEditor);
panel.add(timeSpinner);

return panel;
}

/**

Retrieves details for the given facility name.
@param facilityName The name of the facility to retrieve details for.
@return The Facility object with details for the given facility name.
*/
private Facility retrieveFacilityDetails(String facilityName) {
// Replace this method with code to retrieve facility details from a database or API
// For demo purposes, we will just create a new Facility object with some sample data
return new Facility(facilityName, "Address: " + facilityName + " Address", "Description: " + facilityName + " description");
}
/**

Handles events generated by buttons in the reservation panel.

@param e The ActionEvent object containing information about the event.
*/
private void handleReservationPanelEvents(ActionEvent e) {
if (e.getSource() == reserveBtn) {
// Get reservation details from input fields
String name = nameTxtField.getText();
String email = emailTxtField.getText();
int numGuests = Integer.parseInt(guestsTxtField.getText());
Date date = (Date) dateSpinner.getValue();
date.setHours(((Date) timeSpinner.getValue()).getHours());
date.setMinutes(((Date) timeSpinner.getValue()).getMinutes());
 // Create new booking object
 Booking booking = new Booking(name, email, numGuests, selectedFacility, date);

 // Save booking to database or API
 saveBooking(booking);

 // Display confirmation message
 JOptionPane.showMessageDialog(null, "Reservation successfully made.", "Success", JOptionPane.INFORMATION_MESSAGE);
nameTxtField.setText("");
emailTxtField.setText("");
guestsTxtField.setText("");
tennisCheckBox.setSelected(false);
basketballCheckBox.setSelected(false);
footballCheckBox.setSelected(false);
dateSpinner.setValue(new Date());
timeSpinner.setValue(new Date());

// Reset facility details
facilityDetailsTextArea.setText("");

// Reset reservation ID
reservationIdTxtField.setText("");
} else if (option == JOptionPane.NO_OPTION) {
// Cancel reservation
cancelReservation(reservationID);
JOptionPane.showMessageDialog(null, "Reservation successfully canceled.", "Success", JOptionPane.INFORMATION_MESSAGE);

// Reset reservation ID
reservationIdTxtField.setText("");
}
}
}

/**

Retrieve facility details based on selected facility
@param facilityName name of the facility
@return Facility object containing the details of the facility
*/
private Facility retrieveFacilityDetails(String facilityName) {
// Dummy implementation - replace with actual implementation to retrieve facility details from database
Facility facility = new Facility(facilityName, 100, 2, new String[]{"Tennis", "Basketball", "Football"}, "123 Main St., City A");
return facility;
}
/**

Create reservation details panel based on booking details
@param booking Booking object containing reservation details
@return JPanel object containing reservation details panel
*/
private JPanel createReservationDetailsPanel(Booking booking) {
JPanel reservationDetailsPanel = new JPanel(new GridLayout(0, 2));
reservationDetailsPanel.add(new JLabel("Reservation ID:"));
reservationDetailsPanel.add(new JLabel(booking.getReservationID()));
reservationDetailsPanel.add(new JLabel("Facility Name:"));
reservationDetailsPanel.add(new JLabel(booking.getFacilityName()));
reservationDetailsPanel.add(new JLabel("Date:"));
reservationDetailsPanel.add(new JLabel(booking.getFormattedDate()));
reservationDetailsPanel.add(new JLabel("Time:"));
reservationDetailsPanel.add(new JLabel(booking.getFormattedTime()));
reservationDetailsPanel.add(new JLabel("Number of Guests:"));
reservationDetailsPanel.add(new JLabel(Integer.toString(booking.getNumberOfGuests())));
reservationDetailsPanel.add(new JLabel("Name:"));
reservationDetailsPanel.add(new JLabel(booking.getName()));
reservationDetailsPanel.add(new JLabel("Email:"));
reservationDetailsPanel.add(new JLabel(booking.getEmail()));
reservationDetailsPanel.add(new JLabel("Sport:"));
reservationDetailsPanel.add(new JLabel(booking.getSport()));
return reservationDetailsPanel;
}
/**

Update booking details in database
@param booking Booking object containing updated reservation details
*/
private void updateBookingDetails(Booking booking) {
// Dummy implementation - replace with actual implementation to update booking details in database
}
/**

Cancel reservation in database
@param reservationID ID of the reservation to be canceled
*/
private void cancelReservation(String reservationID) {
// Dummy implementation - replace with actual implementation to cancel reservation in database
}
/**

Main method to launch the reservation system GUI
@param args command line arguments (not used in this program)
*/
public static void main(String[] args) {
ReservationSystem reservationSystem = new ReservationSystem();
reservationSystem.setVisible(true);
}
}
JPanel confirmationPanel = new JPanel(new GridLayout(0, 1));
JLabel confirmationLabel = new JLabel("Confirmation:");
JPanel reservationInfoPanel = new JPanel(new GridLayout(0, 2));
JLabel reservationInfoLabel = new JLabel("Reservation Info:");
JLabel reservationInfoValueLabel = new JLabel();
JPanel customerInfoPanel = new JPanel(new GridLayout(0, 2));
JLabel customerInfoLabel = new JLabel("Customer Info:");
JLabel customerInfoValueLabel = new JLabel();
reservationInfoPanel.add(reservationInfoLabel);
reservationInfoPanel.add(reservationInfoValueLabel);
customerInfoPanel.add(customerInfoLabel);
customerInfoPanel.add(customerInfoValueLabel);
confirmationPanel.add(confirmationLabel);
confirmationPanel.add(reservationInfoPanel);
confirmationPanel.add(customerInfoPanel);

rightPanel.add(customerDetailsPanel);
rightPanel.add(confirmationPanel);

mainPanel.add(topPanel, BorderLayout.NORTH);
mainPanel.add(centerPanel, BorderLayout.CENTER);
mainPanel.add(bottomPanel, BorderLayout.SOUTH);
mainPanel.add(rightPanel, BorderLayout.EAST);

add(mainPanel);

setVisible(true);
}

public static void main(String[] args) {
ReservationSystem reservationSystem = new ReservationSystem();
}

public void actionPerformed(ActionEvent e) {
if (e.getSource() == viewFacilitiesBtn) {
String city = (String)cityComboBox.getSelectedItem();
ArrayList<Facility> facilities = getFacilitiesByCity(city);
String[] facilityNames = new String[facilities.size()];
for (int i = 0; i < facilities.size(); i++) {
facilityNames[i] = facilities.get(i).getName();
}
facilitiesList.setListData(facilityNames);
}
else if (e.getSource() == makeReservationBtn) {
int facilityIndex = facilitiesList.getSelectedIndex();
if (facilityIndex == -1) {
JOptionPane.showMessageDialog(this, "Please select a facility.");
return;
}
Facility facility = getFacilityByName((String)facilitiesList.getSelectedValue());
int guests = 0;
try {
guests = Integer.parseInt(guestsTxtField.getText());
} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(this, "Please enter a valid number of guests.");
return;
}
Date date = (Date)dateSpinner.getValue();
Date time = (Date)timeSpinner.getValue();
if (!isFacilityAvailable(facility, date, time)) {
JOptionPane.showMessageDialog(this, "The selected facility is not available at the selected time.");
return;
}
Booking booking = new Booking(facility, guests, date, time);
bookings.add(booking);
reservationIdTxtField.setText(booking.getReservationId());
updateFacilityDetailsTextArea(facility, guests, date, time);
}
else if (e.getSource() == reserveBtn) {
Facility facility = bookings.get(bookings.size() - 1).getFacility();
int guests = bookings.get(bookings.size() - 1).getGuests();
Date date = bookings.get(bookings.size() - 1).getDate();
Date time = bookings.get(bookings.size() - 1).getTime();
updateCustomerInfoLabel(nameTxtField.getText(), emailTxtField.getText());
updateReservationInfoLabel(facility, guests, date, time);
reserveBtn.setEnabled(false);
confirmBtn.setEnabled(true);
}
else if (e.getSource() == confirmBtn) {
JOptionPane.showMessageDialog(this, "Reservation confirmed.");
resetReservationForm();
}
else if (e.getSource() == cancelBtn) {
resetReservationForm();
}
}

private ArrayList<Facility> getFacilitiesByCity(String city) {
    ArrayList<Facility> facilities = new ArrayList<>();
    // initialize some dummy facilities
    Facility facility1 = new Facility("Facility 1", "Address 1", "Tennis", 5, 20);
    Facility facility2 = new Facility("Facility 2", "Address 2", "Basketball", 10, 30);
    Facility facility3 = new Facility("Facility 3", "Address 3", "Football", 15, 40);

    // add facilities to the list
    facilities.add(facility1);
    facilities.add(facility2);
    facilities.add(facility3);

    // set default facility details
    facilityDetailsTextArea.setText(facility1.toString());

    return facilities;
}

public void actionPerformed(ActionEvent e) {
Object source = e.getSource();
if (source == viewFacilitiesBtn) {
// get selected city
String city = (String) cityComboBox.getSelectedItem();
// filter facilities by sport and capacity
    ArrayList<Facility> filteredFacilities = new ArrayList<Facility>();
    for (Facility facility : facilities) {
        if (facility.getCity().equals(city)) {
            if ((!tennisCheckBox.isSelected() || facility.getSport().equals("Tennis"))
                    && (!basketballCheckBox.isSelected() || facility.getSport().equals("Basketball"))
                    && (!footballCheckBox.isSelected() || facility.getSport().equals("Football"))
                    && Integer.parseInt(guestsTxtField.getText()) <= facility.getCapacity()) {
                filteredFacilities.add(facility);
            }
        }
    }

    // display filtered facilities in the list
    String[] facilityNames = new String[filteredFacilities.size()];
    for (int i = 0; i < filteredFacilities.size(); i++) {
        facilityNames[i] = filteredFacilities.get(i).getName();
    }
    facilitiesList.setListData(facilityNames);

    // set default facility details to the first facility in the list
    if (filteredFacilities.size() > 0) {
        facilityDetailsTextArea.setText(filteredFacilities.get(0).toString());
    } else {
        facilityDetailsTextArea.setText("");
    }
} else if (source == facilitiesList) {
    // display selected facility details
    Facility selectedFacility = facilities.get(facilitiesList.getSelectedIndex());
    facilityDetailsTextArea.setText(selectedFacility.toString());
} else if (source == makeReservationBtn) {
    // show reservation panel
    bottomPanel.setVisible(true);
    // generate reservation ID
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String reservationId = dateFormat.format(new Date());
    reservationIdTxtField.setText(reservationId);
} else if (source == reserveBtn) {
    // create a new booking
    Facility facility = facilities.get(facilitiesList.getSelectedIndex());
    int guests = Integer.parseInt(guestsTxtField.getText());
    Date date = (Date) dateSpinner.getValue();
    Date time = (Date) timeSpinner.getValue();
    String name = nameTxtField.getText();
    String email = emailTxtField.getText();
    Booking booking = new Booking(reservationIdTxtField.getText(), facility, guests, date, time, name, email);
    bookings.add(booking);

    // hide reservation panel
    bottomPanel.setVisible(false);
    // reset reservation details
    reservationIdTxtField.setText("");
    facilityDetailsTextArea.setText("");
    guestsTxtField.setText("");
    dateSpinner.setValue(new Date());
    timeSpinner.setValue(new Date());
    nameTxtField.setText("");
    emailTxtField.setText("");
} else if (source == confirmBtn) {
    // show booking confirmation dialog
    JOptionPane.showMessageDialog(this, "Your booking has been confirmed.");

    // hide reservation panel
    bottomPanel.setVisible(false);
// get the selected facility
Facility selectedFacility = facilities.get(facilityComboBox.getSelectedIndex());

// get the selected date
Date selectedDate = (Date) datePicker.getModel().getValue();

// get the selected time slot
String selectedTimeSlot = timeSlotComboBox.getSelectedItem().toString();

// construct a string with the booking details
String bookingDetails = "Facility: " + selectedFacility.getName() + "\n"
+ "Date: " + selectedDate.toString() + "\n"
+ "Time Slot: " + selectedTimeSlot + "\n"
+ "Booking Confirmation Number: " + bookingConfirmationNumber + "\n";

// display the booking details to the user
JOptionPane.showMessageDialog(this, "Your booking has been confirmed.\n\n" + bookingDetails);

// hide reservation panel
bottomPanel.setVisible(false);

// clear the booking form
clearBookingForm();
       