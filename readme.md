# MULE4 user-importer

**user-importer** è un'app Java per il parsing di CSV e l'invio dei dati via HTTP.

---

## Funzionalità principali

- Lettura di file CSV (cartella locale) con intestazione e separatore `;`.
- Conversione di ogni riga JSON compatibili con l'endpoint.
- Invio dei dati al server `user-api` tramite POST HTTP.
- Log di eseuzione, errori eventuali.

---

## Architettura di base

- **Librerie**: OpenCSV (CSV), Gson (JSON), Apache HttpClient (HTTP).
- **Build & Dipendenze**: Maven.
- **Ambiente**:
  - Java: OpenJDK 21.0.9-ea
  - Maven: 3.9.9
  - OS: Linux 6.17.0-6-generic, amd64

---

## Input

- File CSV con intestazione obbligatoria e separatore `;`.
- Vedi file esempio `user.csv`

---

## Output

- Log su console dell'operazione.
- Vedi file esempio `user-importer.log`

---

## Avvio esecuzione

- **Eseguire file** `start.sh`
  - default file CSV `./users.csv`
  - default base URL `http://192.168.1.10:8080/users`
