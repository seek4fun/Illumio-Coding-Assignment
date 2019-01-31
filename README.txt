Illumio Coding Assignment 2018, PCE teams

Programming language: Java

File descriptiotn:
- main.java.        : testcase for the assignment
- Firewall.java.    : A class with a constructor and a method to check whether a package can pass the firewall
- FirewallRule.java : A class for storing information(direction, protocol, port, ip) of the rules
- test.csv          : rules input for test

How to test: Just simply run the main.java with all the above files in the same directory and check the ouput.

Design:
- In order to group up all the rules from the input file, a FirewallRule class is applied. 
- Hashmap is used as the main data structure
	key  : concatenation of direction and protocol 
	value: a list of FirewallRule the have the same direction and protocal
- Package checking is simple, first check the direction and protocol, if ruleMap contains the key string of the given package, then traverse the corresponding rule list to find out whether the port and the ip address can satisfy one of the rule.

Optimization:
- one thing that can be optimized is to find a effective way to deal with the port and ip address because plenty of overlappings exisit in these two fields.
- Another strategy for optimization is to speed up the matching process of the package and rules, a possible solution will be store rules in an increasing trendency according to the port range or the ip address, then using binary search.

At last, I am interested in Policy team and hoping for your reply.:)