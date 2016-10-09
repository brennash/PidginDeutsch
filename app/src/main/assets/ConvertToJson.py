import sys
import os
import commands
import codecs
import json
import random
from optparse import OptionParser

class ConvertToJson:

	def __init__(self, verboseFlag, inputFilename):

		fileCodec = commands.getoutput('file -b --mime-encoding %s' % inputFilename)
		inputFile = codecs.open(inputFilename, 'r', fileCodec)
		for index, line in enumerate(inputFile):
			index1  = line.index('[')
			index2  = line.rfind(']')
 			deWord  = line[0:index1].rstrip().lstrip()
 			enWord  = line[index2+1:].rstrip().lstrip()
			element = {}
			element['EN'] = enWord
			element['DE'] = deWord
			element['EN-SOUND']   = []
			element['DE-SOUND']   = []
			element['DIFFICULTY'] = round(random.uniform(0.0,1.0),4)
			jsonStr = json.dumps(element, ensure_ascii=False).encode('utf-8')
			print jsonStr

def main(argv):
	parser = OptionParser(usage="Usage: ConvertToJson <input-filename>")

        parser.add_option("-v", "--verbose",
                action="store_true",
                dest="verboseFlag",
                default=False,
                help="Verbose output from the script")

	(options, filename) = parser.parse_args()

	if len(filename) != 1:
		print parser.print_help()
		exit(1)

	check = ConvertToJson(options.verboseFlag, filename[0])
		
if __name__ == "__main__":
    sys.exit(main(sys.argv))
