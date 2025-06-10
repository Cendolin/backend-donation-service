package id.hanifu.cendolin_donation_service.rabbitmq;

public class RabbitExchangesQueues {
//    Produce
    static final String donationExchangeName = "donation.events";
    static final String donationPaidRouteKey = "donation.paid";


//    Subscribe
    static final String userExchangeName = "user.events";
    static final String userCreatedRouteKey = "user.new";

//    Queues
    static final String userCreatedQueueName = "donation.user.created.queue";
}
