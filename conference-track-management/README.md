Synopsis

This is a framework to schedule the conference given the talks list.

The requirement is found out from one of the problem listed over internet.The requirement is as follow:

You are planning a big programming conference and have received many proposals which have passed the initial screen process but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! So you write a program to do it for you.

· The conference has multiple tracks each of which has a morning and afternoon session.
· Each session contains multiple talks.
· Morning sessions begin at 9am and must finish by 12 noon, for lunch.
· Afternoon sessions begin at 1pm and must finish in time for the networking event.
· The networking event can start no earlier than 4:00 and no later than 5:00.
· No talk title has numbers in it.
· All talk lengths are either in minutes (not hours) or lightning (5 minutes).
· Presenters will be very punctual; there needs to be no gap between sessions.

The test output can be of any order, you can schedule it as per your need.

Test input:

Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Common Ruby Errors 45min
Rails for Python Developers lightning
Communicating Over Distance 60min
Accounting-Driven Development 45min
Woah 30min
Sit Down and Write 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
Ruby vs. Clojure for Back-End Development 30min
Ruby on Rails Legacy App Maintenance 60min
A World Without HackerNews 30min
User Interface CSS in Rails Apps 30min

Test output:

Track 1:

09:00AM Writing Fast Tests Against Enterprise Rails 60min
10:00AM Overdoing it in Python 45min
10:45AM Lua for the Masses 30min
11:15AM Ruby Errors from Mismatched Gem Versions 45min
12:00PM Lunch
01:00PM Ruby on Rails: Why We Should Move On 60min
02:00PM Common Ruby Errors 45min
02:45PM Pair Programming vs Noise 45min
03:30PM Programming in the Boondocks of Seattle 30min
04:00PM Ruby vs. Clojure for Back-End Development 30min
04:30PM User Interface CSS in Rails Apps 30min
05:00PM Networking Event

Track 2:

09:00AM Communicating Over Distance 60min
10:00AM Rails Magic 60min
11:00AM Woah 30min
11:30AM Sit Down and Write 30min
12:00PM Lunch
01:00PM Accounting-Driven Development 45min
01:45PM Clojure Ate Scala (on my project) 45min
02:30PM A World Without HackerNews 30min
03:00PM Ruby on Rails Legacy App Maintenance 60min
04:00PM Rails for Python Developers lightning
05:00PM Networking Event
 


About Solution

The problem has been approached in SPI fashion where any consumer wants to override the default behaviour can easily do that
without breaking the existing code. All consumer need to do is to implement the SPI.By default conference talks are scheduled
in First Fit strategy model.

It has following features
- The framework is highly flexible, follows the SOLID design practises.
- Easily extensible.
- Well defined api to use.
- Considerable testcases.
- The track names are not hardcoded, one can easily extend the class to introduce the new track name. 

ConferenceSchedulerServiceProvider is the spi where different implementor can provide their own logic.
Consumer who wish to override the default behaviour. 
 - has to implement the ConferenceSchedulerServiceProvider. 
 - consumer can hook their custom validation logic as well by implementing Validator.
 - implement ConferenceSchedulerStrategy
 - At last consumer need to attach their jar in classpath and pass the provider class name.

The broader domain model is as follow:
Track -> List of Session -> List of Talks
i.e a track can have multiple sessions. A session can have multiple talks.

Steps to run:
JDK requirement 1.8

Eclipse:
- Import the maven project
- Right click the project Run as -> Maven Install

Commandline
- set the maven home
- go to the project
- mvn install
