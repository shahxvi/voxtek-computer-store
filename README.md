# VoxTek Technology Store
CSC186 Group Project

## Members
1. shahxvi
2. llezram
3. relatableandgoofy69

## Documentation
- See [ARCHITECTURE.md](ARCHITECTURE.md) for details about the code structure and design patterns

## How to Build and Run
```bash
cd src
javac Main.java
java Main
```

## Architecture Overview
The application uses a facade pattern where Main.java only interacts with high-level controller classes (Admin, Customer), which handle all background operations (file I/O, authentication, UI dialogs) internally.

For more details, see [ARCHITECTURE.md](ARCHITECTURE.md).

