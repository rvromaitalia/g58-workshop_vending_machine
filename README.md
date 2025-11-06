# 🛠️ Workshop Vending Machine

Your task is to create a **VendingMachine** (candy vending machine) in Java.  
It should be able to:

- Receive money in predetermined amounts
- Let the user choose a product from a list
- Return change

---

## 📦 Step 1: Create Product

Create a package called `model`.

Define one of the following inside the `model` package:

- **Option 1**: Abstract class `Product`
  - ![Abstract class Product.png](img%2FAbstract%20class%20Product.png)
- **Option 2**: Interface `Product`
  - ![Interface Product.png](img%2FInterface%20Product.png)
  
---

## 🧩 Step 2: Extend or Implement Products

- Name the classes clearly to specify the type of product
- Each class must have **at least one unique field**
- Override the needed methods

Options:

- **Abstract class** based structure
  - ![Abstract class.png](img%2FAbstract%20class.png)
- **Interface** based structure
  - ![Interface.png](img%2FInterface.png)
  
---

## 🔧 Step 3: Create Interface and Implementation

1. Create an interface named `VendingMachine`
2. Create a class that implements `VendingMachine`
3. Override all methods from `VendingMachine`

![VendingMachineInterface.png](img%2FVendingMachineInterface.png)
---

## ⚙️ Step 4: Implement Methods

### ➕ Add Currency

- Adds money to the deposit pool
- Only accepts values:  
  `1, 2, 5, 10, 20, 50, 100, 200, 500, 1000`

### 📥 Request

- Buy the requested product
- Only proceeds if enough money is available in the deposit pool

### 💰 End Session

- Returns the deposit pool as change
- Resets the pool to 0

### 📝 Get Description

- Returns a string with the selected product’s description

### 💵 Get Balance

- Returns the current amount in the deposit pool
- Does **not** reset it

### 📋 Get Products

- Returns a `String[]`
- Each string contains a product’s:
    - `id`
    - `name`
    - `price`

