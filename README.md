# delphi-concurent-search
* ProducerService - starts thread at every 5 seconds that produces random amount of items into a list
* SearcherService - starts fixed-size thread pool that searches for specific item in list and replaces it with another item
* SizeChangedListenerThread - listens for a size change of list at background