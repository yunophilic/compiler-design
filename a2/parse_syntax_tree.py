#!/usr/bin/python2
import sys
import os

'''
Run the ANTLR for syntax analysis and generate the AST tree figure
'''

## Parse the arguments
if len(sys.argv) < 3:
    print "Usage: ./parse_syntax_tree.py <test_case_file> <figure_output_file>"
    print "Example: ./parse_syntax_tree.py test_cases/if.txt test_cases/if.png"
    sys.exit(1)

input_file_path = sys.argv[1]
output_file_path = sys.argv[2]

## Generate the syntax analysis results
os.system("java org.antlr.v4.gui.TestRig A2Syntax prog %s > temp.out" % input_file_path)

## Extract the graph definition from temp.out
result_file = open('temp.out')
output_file = open('temp.graph.out','w')
start_record = False

while 1:
    lines = result_file.readlines(100000)
    if not lines:
        break
    for line in lines:

        if line.startswith('PrintGraph'):
            start_record = True
            line_count = 1

        if start_record is not True:
            continue
        elif start_record is True:
            if line_count > 2:
                output_file.write(line)
            line_count += 1

result_file.close()
output_file.close()

## Generate the syntax AST tree and clean the temp files
os.system("dot temp.graph.out -Tpng -o %s" % output_file_path)
os.system("rm temp.graph.out")
os.system("rm temp.out")
os.system("rm test.dot")
