# Makefile for Java

EXEC = Prop.java

#Capas
C1 = Presentacio
C2 = Domini
C3 = Persistencia

S1 = $(wildcard $(C1)/*.java)
S2 = $(wildcard $(C2)/*.java)
S3 = $(wildcard $(C3)/*.java)

CLASS1 = $(S1:.java=.class)
CLASS2 = $(S2:.java=.class)
CLASS3 = $(S3:.java=.class)

all: $(CLASS1) $(CLASS2) $(CLASS3)

clean: 
	rm	 $(CLASS1)
	rm	 $(CLASS2)
	rm	 $(CLASS3)
	
%.class: %.java
	javac $<

