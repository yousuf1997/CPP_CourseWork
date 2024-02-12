from typing import Union
from pydantic import BaseModel
from fastapi import FastAPI

app = FastAPI()


## user model for front end to add movie
class Movie(BaseModel):
    movieName: str
    year: int
    rating: float
    moviePoster: str
    movieDescription: str

@app.post("/add/movie")
def addMovie(movie: Movie):
    return movie