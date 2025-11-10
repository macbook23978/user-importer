# MULE4 user-importer

`user-importer` è un'applicazione Java standalone sviluppata per importare un file CSV nel DB di un app-server.
L'applicazione è progettata per invio flussi dati tramite richieste HTTP POST.

---

## Funzionalità principali

- Lettura di file CSV con intestazione e separatore `;`.
- Conversione di ogni riga JSON compatibili con l'endpoint.
- Invio dei dati al server `user-api` tramite POST HTTP.
- Log di eseuzione, errori eventuali.

---

## Architettura di base

|- CSV (users.csv)
|--- App Java (user-importer)
|------ Endpoint REST: POST /users (user-api)

- `App.java` → classe principale che gestisce il parsing e l'invio.
- Librerie utilizzate:
  - OpenCSV → parsing CSV
  - Gson → conversione JSON
  - Apache HttpClient → invio richieste HTTP
- Il progetto utilizza Maven per gestione dipendenze e build.


---

## Input

- File CSV con intestazione obbligatoria e separatore `;`.
- Colonne previste:  
  1. `firstName` → nome dell'utente  
  2. `lastName` → cognome  
  3. `email` → email (univoca)  
  4. `address` → indirizzo  

**Esempio:**

```csv
firstName;lastName;email;address
Mario;Rossi;mario.rossi@example.com;Via Roma 1, Roma
Luisa;Bianchi;luisa.bianchi@example.it;Corso Torino 22, Torino

---

## Output

- Vedi esempio in file: user-importer.log
- Log su console dell'operazione:

**Esempio:**
JSON inviato: {...}
POST Response: {"id":7,"firstName":"Mario","lastName":"Rossi","email":"mario.rossi@example.com","address":"Via Roma 1, Roma","createdAt":"2025-11-10T22:32:00","updatedAt":"2025-11-10T22:32:00"}

---

## Avvio esecuzione

- Compilare il progetto Maven e esguire app (vedi file `start.sh`).
- Argomenti opzionali: percorso del file CSV (default: ./users.csv)
- Argomenti opzionali: URL dell'API (default: http://192.168.1.10:8080/users)
