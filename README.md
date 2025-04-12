Library v1.0

This project consists of four modules, each with its own functionality:

#### Client Module (UI)
The 'Client' module handles the user interface (UI), allowing users to choose between two actions:
1. Search for books in stock
2. Add a book to the library

This module includes a Kafka consumer and a Kafka producer.

- Case 1: Searching for books
    - The UI provides an "Author" field where the user enters the last name of the desired author.
    - Upon submission, the producer sends a message to the "LuckyTopic" topic.
    - The consumer listens to the "HappyTopic" for a response containing the list of available books by the specified author.
    - The received message is parsed by BookParser, and the UI displays the results in a table.

- Case 2: Adding a new book
    - The UI provides input fields for the author’s last name, book title, and release year.
    - After submission, the producer sends a message to the "BeautifulTopic" topic.
    - The consumer listens to the "WonderfulTopic" for a status message (success or error).

#### Server Module (CRUD Operations)
This module manages database interactions, including:
- Adding books to the database.
- Retrieving books for display.

#### Adapter Module
Acts as a bridge between 'Client' and 'Server'.

- Case 1 (Searching for books):
    - The adapter’s consumer listens to "LuckyTopic" for the author’s last name.
    - It then sends a POST request via RestClient to the 'Server'.
    - The response (list of books) is sent as a message to "HappyTopic".

- Case 2 (Adding a book):
    - The adapter’s consumer listens to "BeautifulTopic" for book details.
    - It sends a POST request to the 'Server'.
    - The operation result (success/failure) is sent to "WonderfulTopic".

#### Common Module (Shared Classes)
Contains two key classes:
1. AddedBook – A POJO representing books to be added or retrieved.
2. Author – Used for searching books in the database.

---

### How to Run the Project
1. Clone the repository: 'git clone https://github.com/PavelRepinsky/Library.git';
2. Start a Kafka Server on an available port (using Docker or another method);
3. Configure VM Options for 'Client' and 'Adapter' modules:
   In IntelliJ IDEA:
    - Go to Run/Debug Configurations.
    - Click Edit Configurations
    - Select the module → Modify Options → Add VM Options.
    - In appeared field 'VM Options' enter: -Dserver.port=<port_number>
(Optional: The same can be done for the 'Server' module if needed.);
4. Run project via IDEA;
5. Open localhost in browser (127.0.0.1);

That’s all! Enjoy using the Library application.  