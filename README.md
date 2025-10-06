# Trello API Automation - Interview Assignment

## Overview
This project automates a Trello workflow using REST API requests. It simulates a task board where cards move through workflow stages (lists) and validates that everything works correctly.

It covers:

1. Board creation and list setup
2. Card creation, movement, and comments
3. Checklist creation and item completion

Tests are written in **Java**, using **RestAssured** and **JUnit 5**.

---

## Prerequisites
- Java 11 or higher installed
- Trello API Key and Token ([Get yours here](https://trello.com/app-key))

---

## Setup and Running Tests



Follow these steps to run the tests:

1. **Clone the repository**

2. **Navigate to the project folder**

3. **Open config.properties (or set environment variables) and add:**
  - api.key=YOUR_TRELLO_KEY
  - api.token=YOUR_TRELLO_TOKEN
  - base.url=https://api.trello.com/1

4. **Run all tests in order using TestSuite.java**

> **Note:** You do not need Maven installed on your system. 
> IntelliJ IDEA's built-in Maven support is enough to run the tests. 
> Open the project in IntelliJ, let it import dependencies from `pom.xml`, and run `TestSuite.java` as described.
