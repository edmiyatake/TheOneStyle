# TopWordFrequency Program – The One Style

## Overview

The `Ten.java` program is implemented using the **The One style** — a functional design where values are wrapped in an abstraction that allows operations to be chained in a pipeline-like sequence using a `bind()` method.

The program:

- Reads an input text file and extracts words.
- Normalizes and cleans the data.
- Removes stop words using a file (`stopwords.txt`).
- Calculates the frequency of each valid word.
- Sorts the results and prints the top 25 most frequent words.

Each processing step is bound to the next using `.bind(...)`, forming a clear and linear flow of transformations.

## The One Style Constraints

- The program defines an **abstraction (`TheOne<T>`)** that wraps a value.
- This abstraction supports:
    - Wrapping values to begin the pipeline.
    - **Binding** functions in sequence using `.bind(...)`, which takes the current value, applies a transformation, and returns a new wrapped value.
    - **Unwrapping** via `.printMe()` to access or display the final result.
- The pipeline is constructed entirely through a chain of `bind()` calls, and no intermediate variables are needed outside the abstraction.

## Folder Structure

Place the following files in the same directory:

- `Ten.java` — Main Java program written in The One style
- `stopwords.txt` — Comma-separated stop words (e.g., `a,an,the,and,...`)
- `input.txt` — Text file to analyze (e.g., `pride-and-prejudice.txt`)

## How to Compile and Run

1. Open a terminal or command prompt.

2. Navigate to your project directory:

   cd path/to/your/folder

3. Compile the Java program:
   javac Ten.java

4. Run the program:
   java Ten pride-and-prejudice.txt

** For the repl.it, the text files will be in the main directory, so you have to use ../../../path to access files from the main parent directory.

Example: java Ten ../../../stopwords.txt ../../../pride-prejudice.txt

## Expected Output
---------------
Top 25 most frequent non-stop words in the input file, printed in descending order:

Example:
mr - 786
elizabeth - 635
very - 488
darcy - 418
such - 395
mrs - 343
much - 329
