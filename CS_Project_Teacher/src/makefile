JC = javac
JCR = java
CLASSES = Main.java

.SUFFIXES: .java .class
.java.class: $(JC) $*.java


default: all

all: ${CLASSES}
	${JC} ${CLASSES}

init: ${CLASSES}
	${JC} ${CLASSES}
	${JCR} Main

clean: rm -rf *.class
