#  Financial Manager App

A modern Kotlin-based Android app for managing personal finances. Users can track income, expenses, and budgets in a clean, responsive UI built using Jetpack Compose and Material3. The app follows the MVVM architecture and stores data locally using Room.

---

## Features

-  Dashboard for viewing balance, income, and expenses
-  Add new income or expense transactions
-  Set budgets per category (monthly/weekly)
-  Dark mode toggle
-  Settings to clear all transactions or budgets
-  Offline support using Room DB

---

##  Tech Stack

| Layer         | Technology            |
|---------------|------------------------|
| UI            | Jetpack Compose + Material3 |
| Architecture  | MVVM (Model-View-ViewModel) |
| Local Storage | Room Persistence Library |
| Language      | Kotlin (K2 compiler compatible) |

---

##  Project Structure

FinancialManager/
├── app/
│ ├── data/
│ │ ├── model/ # Data models (Transaction.kt, Budget.kt)
│ │ ├── dao/ # DAO interfaces
│ │ ├── ExpenseDatabase.kt # Room DB
│ ├── viewmodel/ # ViewModels for data handling
│ ├── ui/
│ │ ├── screens/ # Compose UI screens
│ │ ├── theme/ # App theme and styling
│ │ └── components/ # UI components (BottomNav)
│ ├── navigation/ # Navigation Graph
│ └── MainActivity.kt
└── README.md


---

##  Screenshots

![Screenshot_20250710_074543](https://github.com/user-attachments/assets/11b92978-9230-4a5f-82b6-5d3e1405622d)
![Screenshot_20250710_074543](https://github.com/user-attachments/assets/78751872-c222-4c2e-9c73-f8ac93adf39b)
![Screenshot_20250710_074452](https://github.com/user-attachments/assets/1f08e8a1-1767-4779-a3d3-09d882134ca7)
![Screenshot_20250710_074439](https://github.com/user-attachments/assets/c7768c4b-614c-4a0b-b62c-7119bbe4338b)


##  How to Build

1. Clone the repo:
   ```bash
   git clone https://github.com/Nyaga-467/FinancialManager.git
   cd FinancialManager

Authors
Vincent Nyamao
Emmanuel Mbinji
James Nzau
Dickson Nyaga
Built with ❤️ using Kotlin + Jetpack Compose


