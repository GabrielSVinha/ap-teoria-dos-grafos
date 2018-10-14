
import json
import csv

with open("data/station-network2.json", "r") as myfile:
   s = json.load(myfile)

links = []

print len(s["links"])

for conn in s["links"]:
  src = s["nodes"][(conn["source"] - 1)]
  target = s["nodes"][(conn["target"] - 1)]
  links.append("%s,%s,%s" % (src['name'], target['name'], conn['line']))
  links.append("%s,%s,%s" % (target['name'], src['name'], conn['line']))

with open ("data/mbta-graph2.csv", "w") as mbta:
  mbta.write("Source,Target,Label\n")
  import csv
  writer = csv.writer(mbta, lineterminator='\n')
  for i in links:
    writer.writerow([i])