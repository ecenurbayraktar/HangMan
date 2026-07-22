
# Hangman Game - Java Implementation 

This is a simple version of the classic "Hangman" game implemented in Java for the **CME1212 Algorithms and Programming II** course.

## Assignment Requirements

- Use only **custom Stack and Queue** data structures.
- Do **not** use arrays, ArrayLists, Lists, or Strings in the main solution logic.
- Stack methods allowed: `push`, `pop`, `peek`, `isFull`, `isEmpty`, `size`
- Queue methods allowed: `enqueue`, `dequeue`, `peek`, `isFull`, `isEmpty`, `size`
- Do **not** use Java's built-in Stack/Queue classes.

## Project Structure

- `highscoretable.txt`: Stores the top 12 scores in descending order.
- `animals.txt`: Contains 14 animal names used as words to guess.
- `AnimalStack`: Stack storing animal names.
- `LetterStack`: Stack storing letters A-Z.
- `WordStack`: Stack representing the chosen word’s letters.
- `BoardStack`: Stack for the game board display (`-` for each unknown letter).
- `MissingLetterStack`: Stores incorrect guesses.

##  Game Logic

- Player starts with **120 points**.
- Each wrong guess:
  - **Vowel** → -15 points
  - **Consonant** → -20 points
- One-time **Joker** reveals a random letter.
- Letters guessed again won’t be penalized but will show an error.
- Game ends when:
  - Word is guessed completely, or
  - Score reaches 0 or below.

## High Score System

- Top 12 scores are maintained in a queue (`QName` and `QScore`).
- If a new score qualifies, it's inserted in descending order.
- Written back to `D:\HighScoreTable.txt`.

##  Sample Output

```
Word:  - - - - - - - - -   Misses:       Score: 120   ABCDEFGHIJKLMNOPRSTUVWXYZ
Guess: A
Word:  - A - A - - - - A   Misses:       Score: 120   BCDEFGHIJKLMNOPRSTUVWXYZ
Guess: Z
Word:  - A - A - - - - A   Misses: Z     Score: 100   BCDEFGHIJKLMNOPRSTUVWXY
...
You lost !! Your score is -5.
```

##  How to Run

1. Compile all `.java` files.
2. Place `highscoretable.txt` and `animals.txt` in the working directory.
3. Run the main class and start playing!

##  Author

- Your Name: Ece Nur Bayraktar

---

*This project is developed for academic purposes. Please avoid plagiarism.*
