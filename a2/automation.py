import os
import subprocess
import re
from shutil import rmtree

#constants
TEST_DIR = "./test_cases"
TEST_OUT_DIR = "./out"
SAMPLE_OUT_DIR = "./test_case_output"


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
	if os.path.exists(TEST_OUT_DIR):
		rmtree(TEST_OUT_DIR)
	if os.path.exists("automation.pyc"):
		os.remove("automation.pyc")
	callLinuxCmd("make clean")

def generateOutputFiles():
	for fileName in getFileNames(TEST_DIR):
		srcFilePath = TEST_DIR + "/" + fileName
		outputFilePath = TEST_OUT_DIR + "/" + fileName.replace(".txt", "") + ".png"
		callLinuxCmd("python parse_syntax_tree.py {0} {1}".format(srcFilePath, outputFilePath))

# def getTokens(lines):
# 	tokens = []
# 	for line in lines:
# 		stripped = line[1:len(line)-2]
# 		split = re.split(",(?=(?:[^']*\'[^']*\')*[^']*$)", line)
# 		#print 'split: ' + str(split)
# 		assert len(split) == 4
# 		token = split[1]
# 		tokens.append(token)
# 	#print 'tok: ' + str(tokens) + "\n"
# 	return tokens

def compare(outputFilePath, sampleOutputFilePath):
	f1 = open(outputFilePath, "r")
	f2 = open(sampleOutputFilePath, "r")

	outputFileLines = f1.readlines()
	sampleOutputFileLines = f2.readlines()

	assert len(outputFileLines) == len(sampleOutputFileLines)
	assert outputFileLines == sampleOutputFileLines

	print "test passed for output: {0}, sample: {1}".format(
		outputFilePath, 
		sampleOutputFilePath
	) + "\n"