````markdown
# 🌾 FarmCollector API

A Spring Boot application that collects and reports seasonal planting and harvesting data from farms. The API allows you to submit planting and harvesting records and generate reports by farm and crop per season.

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
    - [POST /planted](#post-planted)
    - [POST /harvested](#post-harvested)
    - [GET /reports/farm/{season}](#get-reportsfarmseason)
    - [GET /reports/crops/{season}](#get-reportscropsseason)
- [Testing with cURL](#testing-with-curl)
- [Troubleshooting](#troubleshooting)

---

## Prerequisites

Before running this application, ensure the following are installed on your machine:

- **Java 21**
- **Maven**
- **cURL** (for testing API endpoints)

### Install Java

- [Java 21 Installation Guide](https://adoptium.net/en-GB/temurin/releases/?version=21)

### Install Maven

- [Maven Installation Guide](https://maven.apache.org/install.html)

---

## Running the Application

### 1. Clone or Download the Project

```bash
git clone https://github.com/your-username/farm-collector-api.git
cd farm-collector-api
````

> Or download and unzip the project manually.

### 2. Run the Application with Maven

```bash
mvn spring-boot:run
```

The application will start on **port 8080** by default.

---

## API Endpoints

### POST `/api/planted`

**Description**: Save planting data submitted by a farmer.

**Request Body** (snake\_case keys matching your `FieldData` entity):

```json
{
  "farm_name":       "Green Valley",
  "season":          "2025-Q2",
  "crop_type":       "Maize",
  "planting_area":   3.5,
  "expected_product":1200
}
```

---

### POST `/api/harvested`

**Description**: Save harvesting data submitted by a farmer.

**Request Body** (snake\_case keys):

```json
{
  "farm_name":      "Green Valley",
  "season":         "2025-Q2",
  "crop_type":      "Maize",
  "actual_product": 1000
}
```

---

### GET `/api/reports/farm/{season}`

**Description**: Returns a seasonal summary report grouped by **farm**.

**Example**:

```http
GET /api/reports/farm/2025-Q2
```

**Sample Response** (snake\_case keys):

```json
[
  {
    "farm_name":      "Green Valley",
    "expected_total": 1200,
    "actual_total":   1000
  }
]
```

---

### GET `/api/reports/crops/{season}`

**Description**: Returns a seasonal summary report grouped by **crop**.

**Example**:

```http
GET /api/reports/crops/2025-Q2
```

**Sample Response** (snake\_case keys):

```json
[
  {
    "crop_type":      "Maize",
    "expected_total": 1200,
    "actual_total":   1000
  }
]
```

---

## Testing with cURL

Use the following `curl` commands in any terminal. Wrap JSON in single quotes on UNIX-like shells.

### ✅ Submit Planting Data

```bash
curl -X POST http://localhost:8080/api/planted \
     -H "Content-Type: application/json" \
     -d '{
           "farm_name":       "Green Valley 2",
           "season":          "2025-Q2",
           "crop_type":       "Beans",
           "planting_area":   3.5,
           "expected_product":1200
         }'
```

### ✅ Submit Harvesting Data

```bash
curl -X POST http://localhost:8080/api/harvested \
     -H "Content-Type: application/json" \
     -d '{
           "farm_name":      "Green Valley",
           "season":         "2025-Q2",
           "crop_type":      "Maize",
           "actual_product": 1000
         }'
```

### ✅ Get Farm Report

```bash
curl http://localhost:8080/api/reports/farm/2025-Q2
```

### ✅ Get Crop Report

```bash
curl http://localhost:8080/api/reports/crops/2025-Q2
```

---

## Troubleshooting

* **Port 8080 already in use**
  Change the port in `src/main/resources/application.properties`:

  ```properties
  server.port=8081
  ```

* **Missing dependencies**
  Run the following command:

  ```bash
  mvn clean install
  ```

* **Lombok not working in IntelliJ**

    1. Install the Lombok plugin:
       `File > Settings > Plugins > Marketplace > Search for 'Lombok'`
    2. Enable annotation processing:
       `File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable`


