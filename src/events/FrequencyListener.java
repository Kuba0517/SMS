package events;
public interface FrequencyListener {
    void frequencyChanged(int newFrequency);
    void setFrequency(int newFrequency);
}