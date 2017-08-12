#!/usr/bin/python2
import sys
import os

'''
Run the ANTLR for syntax analysis and generate the AST tree figure
'''

## Parse the arguments
if len(sys.argv) < 3:
    print "Usage: ./generate_assembly_code.py <test_case_file> <figure_output_file>"
    print "Example: ./generate_assembly_code.py func.txt func.s"
    sys.exit(1)

input_file_path = sys.argv[1]
output_file_path = sys.argv[2]

## Generate the syntax analysis results
os.system("java org.antlr.v4.gui.TestRig MyGram prog %s > %s" % (input_file_path, output_file_path))

