import sys
import os
import codecs
import json
from optparse import OptionParser

class ConvertToJson:

	def __init__(self, verboseFlag, inputFilename):
		inputFile = codecs.open(inputFilename, encoding='utf-8', mode='r')
		for index, line in enumerate(inputFile):
			index1 = line.index('[')
			index2 = line.index(']')
			german = line[0:index1].lstrip().rstrip()
			english = line[index2+1:].lstrip().rstrip()
			element = {}
			element['EN'] = english
			element['DE'] = german
			element['EN-SOUND'] = '{0}_EN.mp3'.format(index+1)
			element['DE-SOUND'] = '{0}_DE.mp3'.format(index+1)
			jsonStr = json.dumps(element)
			print unicode(jsonStr, 'utf-8').encode('utf-8')
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
