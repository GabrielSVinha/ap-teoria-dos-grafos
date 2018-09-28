in_out <- read.csv("turnstile_180901.csv", header = TRUE)
View(in_out)
aggregate(x= in_out$Station.Name, by=list(in_out$Station.Name), FUN)
