# VoxTek Technology Store - Architecture Documentation

## Overview
This document explains the architecture and design patterns used in the VoxTek Technology Store application.

## File Structure

```
src/
├── Main.java                    # Application entry point
├── user/
│   ├── User.java               # Base user class
│   ├── UserUI.java             # Common UI elements
│   ├── admin/
│   │   ├── Admin.java          # Admin controller (main entry point)
│   │   ├── AdminFileHandler.java  # Admin file operations (background)
│   │   └── AdminUI.java        # Admin UI operations (background)
│   └── customer/
│       ├── Customer.java       # Customer controller
│       └── CustomerUI.java     # Customer UI operations
├── product/
│   ├── Product.java            # Base product class
│   ├── Laptop.java             # Laptop product
│   └── Keyboard.java           # Keyboard product
└── input/
    └── Processor.java          # Input processing utilities
```

## Architecture Pattern: Facade Pattern

### Admin Subsystem

The admin functionality follows a **facade pattern** where:

```
Main.java
    ↓
    calls only
    ↓
Admin.java (Facade/Controller)
    ↓
    orchestrates ↙     ↘
    ↓                   ↓
AdminFileHandler    AdminUI
(file operations)   (user interface)
```

### How to Use the Admin Flow

**From Main.java:**
```java
// Main only needs to interact with Admin class
user = new Admin();
Admin.flow(products, (Admin) user, adminFile);
```

**What happens in the background:**
1. `Admin.flow()` calls `AdminFileHandler.initializeAdmin()` to:
   - Load admin credentials from file
   - Create admin file if it doesn't exist
   - Prompt user to create admin account if needed

2. `Admin.flow()` calls `AdminUI.login()` to:
   - Authenticate the admin user
   - Handle login dialog and validation

3. `Admin.flow()` runs the main loop:
   - Uses `UserUI.chooseInventory()` to select inventory type
   - Uses `AdminUI.chooseAddOrRemoveProduct()` for action selection
   - Delegates to `AdminFileHandler` for add/remove operations
   - Delegates to `AdminUI` for product creation dialogs

### Benefits of This Architecture

1. **Separation of Concerns**:
   - `Admin.java` - Business logic and workflow orchestration
   - `AdminFileHandler.java` - File I/O operations
   - `AdminUI.java` - User interface dialogs

2. **Encapsulation**:
   - Main.java doesn't need to know about AdminFileHandler or AdminUI
   - All admin functionality is accessed through a single entry point: `Admin.flow()`

3. **Maintainability**:
   - File handling logic can be changed without affecting UI
   - UI implementation can be changed without affecting file operations
   - Main.java remains simple and clean

4. **Background Operations**:
   - Login happens automatically when Admin.flow() is called
   - File initialization happens automatically
   - Main.java doesn't need to manage these details

## Similar Pattern for Customer

The customer subsystem follows a similar pattern but is less complex:
- `Customer.java` - Customer data model
- `CustomerUI.java` - Customer UI operations and workflow

## Key Design Principles

1. **Single Entry Point**: Main.java only calls one class per user type (Admin or Customer)
2. **Background Processing**: Authentication and file operations happen automatically
3. **Modularity**: Each class has a single, well-defined responsibility
4. **Package-Private Access**: AdminFileHandler and AdminUI are package-private (not public), enforcing that they should only be used by Admin.java

## Future Improvements

- Consider extracting a similar facade pattern for Customer subsystem
- Add interfaces for FileHandler and UI components for better testability
- Consider using dependency injection for better modularity
