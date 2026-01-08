// Example: How to use the Admin flow from Main.java

// This is the CORRECT way - Main only interacts with Admin class
public class ExampleUsage {
    public static void main(String[] args) {
        // Step 1: Create the Admin object
        Admin admin = new Admin();
        
        // Step 2: Call Admin.flow() - this is the SINGLE entry point
        // Everything else (login, file handling, UI) happens automatically
        Admin.flow(products, admin, adminFile);
        
        // That's it! You don't need to:
        // - Call AdminFileHandler.initializeAdmin() manually
        // - Call AdminUI.login() manually
        // - Manage any background operations
        // 
        // Admin.flow() handles everything internally
    }
}

// This demonstrates the facade pattern:
// 
// Main.java (you)
//     │
//     ├─ calls Admin.flow()
//     │
//     └─ Admin.flow() (facade)
//            │
//            ├─ calls AdminFileHandler.initializeAdmin() ← handles file I/O
//            ├─ calls AdminUI.login() ← handles authentication
//            └─ orchestrates the workflow ← runs the main loop
//
// Benefits:
// 1. Main.java stays simple and clean
// 2. Login runs automatically in the background
// 3. File handling runs automatically in the background
// 4. All admin complexity is hidden behind Admin.flow()
