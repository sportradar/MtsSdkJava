A MTS SDK Java library

CHANGE LOG:
2025-07-07 2.4.1.3-17
Security patching
Java 17
WebSocket protocol support

2023-11-30 2.4.1.2
Changed timings on reconnects, updated guides

2022-02-24 2.4.0.3
Fixed bet free stake validation when stake value is 0

2022-02-24 2.4.0.2
Support for ticket version 2.4
- ticket free stake: value, type, description and paidAs

2022-02-24 2.4.0.1
Fixed boosted odds serialization and validation

2022-02-03 2.4.0.0
Support for ticket version 2.4
- ticket bonus: description and paidAs
- ticket selection: boosted odds
- ticket: payCap

2021-06-27 2.3.6.2
Made betDetail serializable

2021-05-27 2.3.6.1
Fixed dto Ticket json schema
Made sdk Ticket serializable

2021-03-22 2.3.6.0
Extending MtsSdk with ReportManager for accessing CcfChange history reports
Fix: removed logging of access token in logs (security)
Fix: minor fixes for javadoc
Fix: minor memory leak for client app

2020-11-06 2.3.5.0
Added # to the User ID pattern
MtsSdk extended with getConnectionStatus - can be used with connection change listener
Fix: make serialization synchronized
Removed ticket selection count limit

2020-04-07 2.3.4.0
Allow 0 cashout stake when building TicketCashout
Added client_properties to rabbit connection
Added argument to rabbit queue declare: queue-master-locator
Examples updated to use UOF markets
Changed connection heartbeat from 45s to 20s
Changed jackson.version from 2.6.5 to 2.10.1

2019-10-25 2.3.3.0
Added onTicketResponseTimedOut handler in TicketResponseHandler
Added configuration property ticketResponseTimeoutPrematch
Added setTicketResponseTimeoutLive and setTicketResponseTimeoutPrematch to SdkConfigurationBuilder
Default timeout for ticket response for live selections increased from 15s to 17s
Added new distribution channels

2019-07-25 2.3.2.0
Made Ticket objects serializable
Removed use of singleton for CustomBetSelectionBuilder
Added Content-Type to AdditionalInfo property of response tickets
Fix: CustomBet can be set without odds
Fix: Selection Odds changed from int to Integer

2019-05-30 2.3.1.0
Support for custom bet
Added getCustomBetManager to MtsSdkApi
Exposed custom bet fields on Ticket

2019-05-10 2.3.0.0
Support for ticket version 2.3
Support for non-Sportradar ticket settlement
Added lastMatchEndTime to Ticket and TicketBuilder
Added TicketNonSrSettle and TicketNonSrSettleBuilder

2019-02-27 1.8.0.0
Added support for Client API - added method getClientApi on MtsSdkApi
Added configuration for ticket, ticket cancellation and ticket cashout message timeouts

2019-02-07 1.7.0.0
Adding acking on consumers message processed
Added AutoAcceptedOdds to ITicketResponse
Added AdditionalInfo to all ticket responses
Fix: settings corrected for sending ticket cancel and reoffer cancel message
Fix: default langId in EndConsumer to null

2018-11-28 1.6.0.0
Support for ticket version 2.2
Added AutoAcceptedOdd to TicketResponse
Added TotalCombinations to Ticket and TicketBuilder
Added BetCashout to TicketCashout - support for partial cashout
Added BetCancel to TicketCancel - support for partial cancellation
Removed deletion of consumer queues on close
Reviewed and updated documentation and properties files

2018-10-05 1.5.0.0
Added 'exclusiveConsumer' property to the configuration, indicating should the rabbit consumer channel be exclusive (default is true)
Added YAML config support
Added Ticket response timeout callback (Ticket, TicketCancel, TicketCashout) to notify user if the ticket response did not arrive in timely fashion (when sending in non-blocking mode)
Added timeout when fetching MarketDescriptions from API fails (30s)
Improved handling and logging for market description (for UF markets)
Fix: BetBonus value condition - if set, must be greater then zero
Minor fixes and improvements

2018-03-26 1.4.0.0
SenderBuilder.confidence changed from long to Long
RabbitMQ TLS validation
Exposure of ticket JSON payloads trough SdkTicket.getJsonValue
Various small fixes and improvements

2018-01-17 1.3.0.0
support for ticket and ticket response v2.1:
- selection id max length increased to 1000
- bet bonus decreased to min 0
- deprecated response internal message
- added rejection info to ticket response selection
- added response additional info
- bet id is now nullable
- specific sender channel validation removed
- end customer can now have all properties nullable
- sum of wins is now nullable

2017-12-21 1.2.1.0
Fix: TicketBuilder for multi-bet tickets with same selections but different odds or different banker value
Fix: TicketReofferBuilder

2017-11-16 1.2.0.0
Added support for setting port to SdkConfigurationBuilder

2017-10-19 1.1.5.0
Added SdkConfigurationBuilder for building SdkConfiguration

2017-10-16 1.1.4.0
Sender.Currency property updated to support also 4-letter sign (i.e. mBTC)

2017-09-13 1.1.3.0
Added new settings property 'provideAdditionalMarketSpecifiers'
Fix: building selection id with UF specifiers
Fix: removed precondition check for selectionDetails in ticket response
Fix: removed requirement for EndCustomer.Id for Terminal and Retail sender channel

2017-08-28 1.1.2.0
Exposed property CorrelationId in all tickets and ticket responses
Refined logging within sdk (for feed and rest traffic)
Property SelectionDetails on ticket response changed to optional
Updated example with new BuilderFactory (available on MtsSdk instance)
Internal: 'selectionRef:[]' removed from json when empty
Internal: added ConsumerTag to consumer channels

2017-07-10 1.1.1.1
Sender requirements - "terminal" channel endCustomer NPE fix

2017-07-06 1.1.1.0
Logback logging framework removed from the SDK, so any logging framework can now be used
Sender requirements updated
Sender channel phone removed
Selection event id type changed from long to String
AMQP message delivery_mode changed from 1 to 2
Minor bug fixes

2017-05-15 1.1.0.0
Added ticket reoffer support
Added cashout ticket support
Minor bug fixes

2017-04-06 1.0.0.0-beta
Initial release (supports MTS tickets version 2.0)
