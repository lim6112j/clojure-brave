(ns clojure-noob.ch10.exercise)

class CuddleZombie:
#attr_accessor is just a shorthand way for creating getters and
#setters for the listed instance variables
attr_accessor :cuddle_hunger_level, :percent_deteriorated

def initialize (cuddle_hunter_level = 1, percent_deteriorated = 0):
self.cuddle_hunger_level = cuddle_hunter_level
self.percent_deteriorated = percent_deteriorated
end
end

