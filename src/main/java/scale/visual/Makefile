###############################################################################
#
# Copyright 2008 by the
# <a href="http://ali-www.cs.umass.edu/">Scale Compiler Group</a>,<br>
# <a href="http://www.cs.umass.edu/">Department of Computer Science</a><br>
# <a href="http://www.umass.edu/">University of Massachusetts</a>,<br>
# Amherst MA. 01003, USA<br>
# All Rights Reserved.<br>
# <p>
# Compiles the java classes and recurses to sub-directories
#
# Makefiles that include this makefile should have rules called
#   all    - make everything
#   clean  - remove everything that is built
# These rules should depend on java, javaall, and javaclean.
# <p>
# $Id: Makefile,v 1.19 2006-02-24 19:08:25 burrill Exp $
###############################################################################
PACKAGE    := scale/visual
DEST       := $(CLASSDEST)/$(PACKAGE)

CLASSFILES :=	$(DEST)/DaVinci.class \
		$(DEST)/SGDRhombus.class \
		$(DEST)/SGD.class \
		$(DEST)/Vcg.class \
		$(DEST)/External.class

MODULES    := 

visual: java

all: javaall

clean: javaclean

include $(SCALE)/$(SCALERELEASE)/scale/Makefile.defs
