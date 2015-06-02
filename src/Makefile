# Makefile for Java

EXEC = Prop.java
empty :=

#Capas
C1 = Presentacio
C2 = Domini
C3 = Persistencia

S1 = $(wildcard $(C1)/*.java)
R1 = $(subst $(C1)/,$(empty),$(S1))
S2 = $(wildcard $(C2)/*.java)
R2 = $(subst $(C2)/,$(empty),$(S2))
S3 = $(wildcard $(C3)/*.java)
R3 = $(subst $(C3)/,$(empty),$(S3))

CLASS1 = $(S1:%.java=%.class)
CLASS2 = $(S2:%.java=%.class)
CLASS3 = $(S3:%.java=%.class)

CLASSPATH = GraphStream/gs-core-1.3.jar:GraphStream/gs-ui-1.3.jar
SOURCEPATH = .

all: $(CLASS1) $(CLASS2) $(CLASS3)

clean: 
	rm	 $(CLASS1)
	rm	 $(CLASS2)
	rm	 $(CLASS3)
	
%.class: %.java
	javac -classpath $(CLASSPATH) -sourcepath $(SOURCEPATH) $<

print-%: ; @echo $* = $($*)

