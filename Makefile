.phony: all run clean

NAME = "Main"
SRC = "src"
BIN = "bin"

all:
	@echo "Compiling..."
	javac -d bin src/*.java

run: all
	@echo "Running..."
	java -classpath $(BIN) $(NAME) 

clean:
	rm bin/*.class
