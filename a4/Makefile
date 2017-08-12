# Configure Java compiler
JC = javac
JFAGS = -g
ANLTR = java -Xmx500M org.antlr.v4.Tool
PKG = jar
PKGPRA = cvfm

# Configure output
OUTPUT = ./

# Configure source files
SRCS = ./*.java

# Configure output class
CLASSES = *.class

# target
default:init $(CLASSES)

init:
	$(ANLTR) MyGram.g4

# Compile
$(CLASSES):
	$(JC) $(JFAGS) $(SRCS) -d $(OUTPUT)
	$(JC) $(JFAGS) ./x86/*.java -d ./x86/
clean:
	-rm -r *.class *.java *.tokens
	-rm -r ./x86/*.class
