package companies.yandex.yandex.contest.CTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class CTask {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        JSONParser parser = new JSONParser();
        List<Offer> input = new ArrayList<>();

        int feedCount = Integer.parseInt(r.readLine());

        for (int i = 0; i < feedCount; ++i) {
            JSONObject jsonFeed = (JSONObject) parser.parse(r.readLine());
            JSONArray jsonFeedOffers = (JSONArray) jsonFeed.get("offers");
            jsonFeedOffers.forEach(offer -> {
                JSONObject offerJson = (JSONObject) offer;
                input.add(new Offer(String.valueOf(offerJson.get("offer_id")),
                        Integer.parseInt(String.valueOf(offerJson.get("market_sku"))),
                        Integer.parseInt(String.valueOf(offerJson.get("price")))));
            });
        }
        Feed result = new Feed(new ArrayList<>());
        result.getOffers().addAll(input.stream()
                .sorted((a, b) -> new OfferComparator().compare(a, b))
                .collect(Collectors.toList()));
        System.out.println(JSONValue.toJSONString(result.toJSONObject()));
    }

    private static class Offer {
        private final String offer_id;
        private final int market_sku;
        private final int price;

        public Offer(String offer_id, int market_sku, int price) {
            this.offer_id = offer_id;
            this.market_sku = market_sku;
            this.price = price;
        }

        public String getOffer_id() {
            return offer_id;
        }

        public long getPrice() {
            return price;
        }

        @SuppressWarnings("unchecked")
        public JSONObject toJSONObject() {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("offer_id", this.offer_id);
            jsonObject.put("market_sku", this.market_sku);
            jsonObject.put("price", this.price);
            return jsonObject;
        }
    }

    private static class Feed {
        private final Collection<Offer> offers;

        public Feed(Collection<Offer> offers) {
            this.offers = offers;
        }

        public Collection<Offer> getOffers() {
            return offers;
        }

        public JSONObject toJSONObject() {
            Map<String, List<JSONObject>> map = new HashMap<>();
            map.put("offers", this.offers.stream()
                    .map(Offer::toJSONObject)
                    .collect(Collectors.toList()));
            return new JSONObject(map);
        }
    }

    private static class OfferComparator implements Comparator<Offer> {

        @Override
        public int compare(Offer o1, Offer o2) {
            if (o1.getPrice() < o2.getPrice()) {
                return -1;
            }
            if (o1.getPrice() > o2.getPrice()) {
                return 1;
            }
            return o1.getOffer_id().compareTo(o2.getOffer_id());
        }
    }
}
