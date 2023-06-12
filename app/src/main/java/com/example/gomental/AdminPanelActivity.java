package com.example.gomental;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class AdminPanelActivity extends AppCompatActivity {

    private Button btnViewUsers;
    private Button btnDeleteUsers;
    private Button btnAddUsers;
    private Button btnUpdateUsers;

    private Button btnViewMedicines;
    private Button btnDeleteMedicines;
    private Button btnAddMedicines;
    private Button btnUpdateMedicines;

    private Button btnViewOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        btnViewUsers = findViewById(R.id.btnViewUsers);
        btnDeleteUsers = findViewById(R.id.btnDeleteUsers);
        btnAddUsers = findViewById(R.id.btnAddUsers);
        btnUpdateUsers = findViewById(R.id.btnUpdateUsers);

        btnViewMedicines = findViewById(R.id.btnViewMedicines);
        btnDeleteMedicines = findViewById(R.id.btnDeleteMedicines);
        btnAddMedicines = findViewById(R.id.btnAddMedicines);
        btnUpdateMedicines = findViewById(R.id.btnUpdateMedicines);

        btnViewOrders = findViewById(R.id.btnViewOrders);



        // Set click listeners for each button
        btnViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> userList = getUsersFromDatabase();


                showUserList(userList);
                Toast.makeText(AdminPanelActivity.this, "View Users clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to delete users
                // Show a dialog or confirmation message to confirm the deletion
                showDialog("Delete Users", "Are you sure you want to delete the selected users?");
            }
        });

        btnAddUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to add users
                // Open a form or dialog to add a new user
                openAddUserDialog();
            }
        });

        btnUpdateUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to update users
                // Open a form or dialog to update user details
                Toast.makeText(AdminPanelActivity.this, "Update Users clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to view medicines stock and reports inventory
                // Display the list of medicines and their stock and reports inventory
                Toast.makeText(AdminPanelActivity.this, "View Medicines clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnDeleteMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to delete medicines
                // Show a dialog or confirmation message to confirm the deletion
                showDialog("Delete Medicines", "Are you sure you want to delete the selected medicines?");
            }
        });

        btnAddMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to add medicines
                // Open a form or dialog to add a new medicine
                Toast.makeText(AdminPanelActivity.this, "Add Medicines clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdateMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to update medicines
                // Open a form or dialog to update medicine details
                Toast.makeText(AdminPanelActivity.this, "Update Medicines clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the functionality to view orders
                // Display the list of orders
                Toast.makeText(AdminPanelActivity.this, "View Orders clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //view users method
    // Function to retrieve the list of registered users from the database
    private ArrayList<String> getUsersFromDatabase() {
        // Initialize an empty ArrayList to store the user data
        ArrayList<String> userList = new ArrayList<>();

        // TODO: Retrieve the list of registered users from the database
        // You can use the Database class methods or your preferred approach to fetch the user data

        // For example, if you have a Database object named "database":
        // userList = database.getAllUsers();

        // Return the list of registered users
        return userList;
    }

    // Function to display the list of registered users
    private void showUserList(ArrayList<String> userList) {
        // Create a StringBuilder to build the user list string
        StringBuilder userListString = new StringBuilder();

        // Append each user to the StringBuilder
        for (String user : userList) {
            userListString.append(user).append("\n");
        }

        // Display the user list using a Toast message or any other desired method
        Toast.makeText(AdminPanelActivity.this, userListString.toString(), Toast.LENGTH_LONG).show();
    }



    // Function to show a dialog or confirmation message
    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminPanelActivity.this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Perform the deletion of users
                        deleteUsers();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel the deletion process
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

        // Function to delete users
        private void deleteUsers() {
            // TODO: Implement the functionality to delete users
            // Delete the selected users from the database
            // You can use the Database class methods or your preferred approach to delete the users

            // Show a toast message or perform any other desired actions to indicate successful deletion
            Toast.makeText(AdminPanelActivity.this, "Users deleted successfully", Toast.LENGTH_SHORT).show();
        }

    // Function to open a form or dialog to add a new user
    private void openAddUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminPanelActivity.this);
        builder.setTitle("Add User")
                .setMessage("Enter user details:")
                .setView(R.layout.add_user_dialog)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Retrieve the entered user details from the dialog
                        Dialog dialogView = (Dialog) dialog;
                        EditText etUsername = dialogView.findViewById(R.id.etUsername);
                        EditText etEmail = dialogView.findViewById(R.id.etEmail);
                        EditText etPassword = dialogView.findViewById(R.id.etPassword);
                        EditText etRole = findViewById(R.id.etRole);

                        String username = etUsername.getText().toString().trim();
                        String email = etEmail.getText().toString().trim();
                        String password = etPassword.getText().toString().trim();
                        String role = etRole.getText().toString().trim();

                        // Validate the entered user details
                        if (validateUserDetails(username, email, password,role)) {
                            // Add the new user to the database
                            addUser(username, email, password,role);

                            // Show a toast message or perform any other desired actions to indicate successful addition
                            Toast.makeText(AdminPanelActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // Show a toast message or perform any other desired actions to indicate invalid user details
                            Toast.makeText(AdminPanelActivity.this, "Invalid user details", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel the addition of a new user
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Function to validate the entered user details
    private boolean validateUserDetails(String username, String email, String password,String role) {
        // TODO: Implement the validation logic for username, email, and password
        // Return true if the user details are valid, otherwise return false
        // You can perform checks such as empty fields, proper email format, etc.

        return !TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
    }

    // Function to add a new user to the database
    private void addUser(String username, String email, String password, String role) {
        // TODO: Implement the functionality to add a new user to the database
        // Add the user details to the database using the Database class methods or your preferred approach
        // You can use the provided Database class and its register() method to add the user

        Database database = new Database(AdminPanelActivity.this, "GoMental.db", null, 1);
        boolean isRegistered = database.register(username, email, password,role);

        if (isRegistered) {
            // User added successfully
        } else {
            // Failed to add user
        }
    }
}
