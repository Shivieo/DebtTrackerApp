# Debt Tracker (Sole Proprietor Edition)
*Managing debt isn't just about math; it's about business survival.*

## Why I built this
After 12th grade, I spent 3 years managing my family's business through a period of high debt. I was the one doing the manual entries every day. I realized that general accounting apps are too complicated for small shop owners, and simple calculators don't track "hidden" debt like outstanding salaries or utility bills.

I built this app to solve the exact problems I faced in that shop. It’s designed for speed and clarity so a business owner can see their "Debt Condition" in one tap.

## The Tech Stuff (What's under the hood)
I didn't want a "laggy" app when dealing with financial data, so I focused on a clean architecture:

* **Jetpack Compose:** Used for the entire UI. I prefer it over XML because it makes handling dynamic state (like the dropdowns and live debt calculations) much easier.
* **MVVM & Room:** I keep the business logic inside the ViewModel. All debt entries are saved locally using Room DB. Even if the phone restarts, the data is safe.
* **The "Debt Condition" Logic:** This is the core. It doesn't just subtract Sales from Debt. It accounts for `Credit Purchases` + `Outstanding Rent/Salary` - `Sales`. 
* **Coroutines:** I use these to make sure database writes don't freeze the UI.

## Core Calculation
The app calculates health using this logic:
`Current State = Last Debt + New Credits - (Sales - Operating Expenses)`

## Challenges & Learnings
* **State Management:** Handling three different dropdown menus in Compose was tricky at first. I learned how to use `remember` and `mutableStateOf` to prevent the UI from resetting unexpectedly.
* **Data Types:** I quickly realized that `Int` can overflow with large business numbers, so I'm moving toward `Long` for financial safety.

## How to run it
1. Clone the repo.
2. Open in Android Studio (Koala or later).
3. Build and run on an emulator or physical device (API 24+).

---
**Author:**
Shivam Barnwal 
*Student @ Manipal University | 200+ DSA Problems Solved | Data Science @ PWSkills*
