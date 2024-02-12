from main.api.main import Movie
import psycopg2
from psycopg2 import OperationalError

class DBService:
    def __init__(self):
        pass

    def addMovie(self, movie: Movie):
        pass

    def _create_connection(db_name, db_user, db_password, db_host, db_port):
        connection = None
        try:
            connection = psycopg2.connect(
                database=db_name,
                user=db_user,
                password=db_password,
                host=db_host,
                port=db_port,
            )
            print("Connection to PostgreSQL DB successful")
        except OperationalError as e:
            print(f"The error '{e}' occurred")
        return connection
