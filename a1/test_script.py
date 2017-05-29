#!/usr/bin/python

import os
import subprocess
import re
from shutil import rmtree

#constants
LEXER_NAME = "A1Lexer"
TEST_DIR = "./test_cases"
TEST_OUT_DIR = "./test_cases_out"
SAMPLE_OUT_DIR = "./sample_output"


def callLinuxCmd(cmdString):
	output = subprocess.check_output(cmdString.split())
	print cmdString
	print output
	return output

def getFileNames(dirPath):
	ret = []
	for (_, _, fileNames) in os.walk(dirPath):
		ret.extend(fileNames)
	return set(ret)

def setup():
	if not os.path.exists(TEST_OUT_DIR):
		os.makedirs(TEST_OUT_DIR)
	callLinuxCmd("make clean")
	callLinuxCmd("make")

def cleanup():
	rmtree(TEST_OUT_DIR)
	callLinuxCmd("make clean")

def generateOutputFiles():
	for fileName in getFileNames(TEST_DIR):
		srcFilePath = TEST_DIR + "/" + fileName
		outputFilePath = TEST_OUT_DIR + "/" + fileName + ".out"
		output = callLinuxCmd("java org.antlr.v4.gui.TestRig {0} tokens -tokens {1}"
			.format(LEXER_NAME, srcFilePath, outputFilePath))
		f = open(outputFilePath,"w+")
		f.write(output)
		f.close()

def getTokens(lines):
	tokens = []
	for line in lines:
		stripped = line[1:len(line)-2]
		split = re.split(",(?=(?:[^']*\'[^']*\')*[^']*$)", line)
		#print 'split: ' + str(split)
		assert len(split) == 4
		token = split[1]
		tokens.append(token)
	#print 'tok: ' + str(tokens) + "\n"
	return tokens

def compareTokens(outputFilePath, sampleOutputFilePath):
	f1 = open(outputFilePath, "r")
	f2 = open(sampleOutputFilePath, "r")

	outputFileLines = f1.readlines()
	sampleOutputFileLines = f2.readlines()

	assert len(outputFileLines) == len(sampleOutputFileLines)

	outputFileTokens = getTokens(outputFileLines)
	sampleOutputFileTokens = getTokens(sampleOutputFileLines)

	assert outputFileTokens == sampleOutputFileTokens

	print "test passed for output: {0}, sample: {1}".format(
		outputFilePath, 
		sampleOutputFilePath
	) + "\n"


#main
setup()

generateOutputFiles()

outputFiles = getFileNames(TEST_OUT_DIR)
sampleOutputFiles = getFileNames(SAMPLE_OUT_DIR)
assert outputFiles == sampleOutputFiles

for fileName in outputFiles:
	compareTokens(TEST_OUT_DIR + "/" + fileName, SAMPLE_OUT_DIR + "/" + fileName)

cleanup()
