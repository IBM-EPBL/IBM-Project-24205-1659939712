import sqlite3

conn = sqlite3.connect('person_database.db')
print("Opened db successfully")

conn.execute('CREATE TABLE person (name TEXT, addr TEXT, city TEXT, pin TEXT)')
print("Table Created")

conn.close()