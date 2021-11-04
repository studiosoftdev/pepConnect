NAMES = PCS
NAMEC = PCC

bs:
	javac $(NAMES).java
rs:
	java $(NAMES)
brs:
	javac $(NAMES).java
	java $(NAMES)

bc:
	javac $(NAMEC).java
rc:
	java $(NAMEC)
brc:
	javac $(NAMEC).java
	java $(NAMEC)