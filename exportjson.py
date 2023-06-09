#!/usr/bin/env python3

import openpyxl
import json

# Open the Excel file
workbook = openpyxl.load_workbook('testcases.xlsx')

sheet_name = 'testcases'
sheet = workbook[sheet_name]

root = {}
testcases = []
rowindex = 0
colindex = 0
for row in sheet.iter_rows():
    # Row 0 will contain the column headers, but id doesn't matter
    # we also include it as a testcase
    testcase = {}
    if (row[0].value is not None):
        testcase['id'] = row[0].value
    else:
        testcase['id'] = ''
    
    if (row[1].value is not None):
        testcase['description'] = row[1].value
    else:
        testcase['description'] = ''
    
    if (row[2].value is not None):
        testcase['action'] = row[2].value
    else:
        testcase['action'] = ''
    
    if (row[3].value is not None):
        testcase['expectedResult'] = row[3].value
    else:
        testcase['expectedResult'] = ''

    testcases.append(testcase)
workbook.close()
root['testcases'] = testcases

json_string = json.dumps(root, indent = 4)
print(json_string)