Local Alias Directory (LAD)
===========================

The **Local Alias Directory (LAD)** is a localized version of the National Alias Directory (NAD) designed for institutions/banks. LAD acts as a proxy between banks and NAD, handling all API requests to NAD while maintaining a local copy of successful responses for the bank's reference.

Features
--------

- **Mirrored NAD APIs**: LAD exposes endpoints identical to NAD, ensuring seamless integration for banks.
- **Local Data Storage**: Saves successful responses locally for individuals, merchants, aliases, etc., except for lookup operations.
- **Dynamic Schema Support**: Handles both `IBAN` and `ALIAS` schemas dynamically.
- **Secure PIN Management**: Encrypts and stores PINs locally for customers and merchants.

Project Structure
-----------------

```
src/
├── main/
│   ├── java/
│   │   └── ly/kaizen/lad/
│   │       ├── controller/      # Controllers for handling API requests
│   │       ├── dto/             # Data Transfer Objects (DTOs)
│   │       ├── model/           # Entity definitions
│   │       ├── repository/      # Spring Data JPA repositories
│   │       ├── service/         # Service interfaces and implementations
│   │       └── util/            # Utility classes
│   └── resources/
│       ├── application.yml      # Configuration properties
│       └── db/                  # Database migration scripts
└── test/
    └── java/ly/kaizen/lad/      # Unit and integration tests
```

Endpoints
---------

All endpoints mirror NAD APIs and are available under `/api/v1`.

### Individuals
- `POST /individuals/enroll` - Enroll an individual in NAD via LAD.
- `POST /individuals/update` - Update an individual's details.
- `GET /individuals/lookup` - Lookup an individual using `IBAN` or `ALIAS`.

### Merchants
- `POST /merchants/enroll` - Enroll a merchant in NAD via LAD.
- `POST /merchants/update` - Update a merchant's details.
- `GET /merchants/lookup` - Lookup a merchant using `IBAN` or `ALIAS`.



Configuration
-------------

Configure the LAD application in `application.yml`:

```yaml
nad:
  base-url: https://nad.example.com/api/v1
```

Setup
-----

1. Clone the repository.
2. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```
3. Run the application:
   ```bash
   java -jar build/libs/lad-0.0.1-SNAPSHOT.jar
   ```

License
-------

This project is made by Muhammad Tellesy and licensed under the MIT License.
