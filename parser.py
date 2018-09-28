import json
import csv

with open("station-network.json", "r") as myfile:
   s = json.load(myfile)


links = []

for conn in s["links"]:
  src = s["nodes"][conn["source"] - 1]
  target = s["nodes"][conn["target"] - 1]
  links.append("%s,%s,%s" % (src['id'], target['id'], conn['line']))
  links.append("%s,%s,%s" % (target['id'], src['id'], conn['line']))

with open ("mbta-graph.csv", "w") as mbta:
  import csv
  writer = csv.writer(mbta, lineterminator='\n')
  for i in links:
    writer.writerow([i])
