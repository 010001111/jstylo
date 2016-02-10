package edu.drexel.psal.jstylo.generics;

import com.jgaap.generics.*;

import java.io.Serializable;
import java.util.*;

/**
 * The Feature Driver class is designed to hold a configuration for feature set extraction, based on a JGAAP event driver,
 * list of JGAAP canonicizers to apply on the documents parsed by this event driver, list of JGAAP event cullers to apply
 * on the extracted event sets and normalization configuration - pair of normalized technique and factoring constant. 
 * 
 * @author Ariel Stolerman
 *
 */
public class FeatureDriver implements Serializable{
	
	/* ======
	 * fields
	 * ======
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the feature.
	 */
	private String name;
	
	/**
	 * The description of the feature.
	 */
	private String description;
	
	/**
	 * The JGAAP event driver to use.
	 */
	private EventDriver underlyingEventDriver;
	
	/**
	 * Defines whether to calculate a value from a histogram generated from the underlying event driver's
	 * event set output, or simply take a numeric value from the first (and only) event in that event set.
	 */
	private boolean calcHist;
	
	/**
	 * The list of JGAAP canonicizers to apply for this event driver.
	 */
	private List<Canonicizer> canonicizers;
	
	/**
	 * The list of JGAAP event cullers to apply for this event driver.
	 */
	private List<EventCuller> cullers;
	
	/**
	 * The normalization and factorization to apply at the features (events) extracted by this event driver.
	 */
	private Pair<NormBaselineEnum,Double> norm;
	
	
	/*
	 * ============
	 * constructors
	 * ============
	 */
	
	/**
	 * Default Constructor.
	 */
	public FeatureDriver() {
		canonicizers = new ArrayList<Canonicizer>();
		cullers = new ArrayList<EventCuller>();
		norm = new Pair<NormBaselineEnum, Double>(NormBaselineEnum.NONE, null);
	}
	
	/**
	 * Constructor for a feature driver, initialized with the given event driver, empty list of canonicizers,
	 * empty list of event cullers and no normalization and factoring.
	 * @param name
	 * 		The name of the feature driver. Should be short.
	 * @param calcHist
	 * 		Defines whether to calculate histogram of the output event set, or simply take the numeric value
	 * 		of the only event in that event set.
	 * @param underlyingEventDriver
	 * 		The JGAAP event driver to be used.
	 */
	public FeatureDriver(String name, boolean calcHist, EventDriver underlyingEventDriver) {
		this();
		this.name = name;
		this.calcHist = calcHist;
		this.underlyingEventDriver = underlyingEventDriver;
	}
	
	/**
	 * Constructor for a feature driver, initialized with the given event driver, list of canonicizers,
	 * list of event cullers and no normalization and factoring.
	 * @param name
	 * 		The name of the feature driver. Should be short.
	 * @param calcHist
	 * 		Defines whether to calculate histogram of the output event set, or simply take the numeric value
	 * 		of the only event in that event set.
	 * @param underlyingEventDriver
	 * 		The JGAAP event driver to be used.
	 * @param canonicizers
	 * 		The list of canonicizers to be applied on the parsed documents when applying this feature driver.
	 * @param cullers
	 * 		The list of event cullers to be applied when applying this feature driver.
	 */
	public FeatureDriver(String name, boolean calcHist, EventDriver underlyEventDriver, List<Canonicizer> canonicizers, List<EventCuller> cullers) {
		this(name,calcHist,underlyEventDriver);
		this.canonicizers = canonicizers;
		this.cullers = cullers;
	}
	
	/**
	 * Constructor for a feature driver, initialized with the given event driver, empty list of canonicizers,
	 * empty list of event cullers, but with normalization and factoring.
	 * @param name
	 * 		The name of the feature driver. Should be short.
	 * @param calcHist
	 * 		Defines whether to calculate histogram of the output event set, or simply take the numeric value
	 * 		of the only event in that event set.
	 * @param underlyingEventDriver
	 * 		The JGAAP event driver to be used.
	 * @param normalization
	 * 		The pair of normalization technique and factor to be applied on the event set generated by this feature driver. n
	 */
	public FeatureDriver(String name, boolean calcHist, EventDriver underlyEventDriver, Pair<NormBaselineEnum,Double> normalization) {
		this(name,calcHist,underlyEventDriver);
		this.norm = normalization;
	}
	
	/**
	 * Constructor for a feature driver, initialized with the given event driver, list of canonicizers,
	 * list of event cullers, normalization and factoring.
	 * @param name
	 * 		The name of the feature driver. Should be short.
	 * @param calcHist
	 * 		Defines whether to calculate histogram of the output event set, or simply take the numeric value
	 * 		of the only event in that event set.
	 * @param underlyingEventDriver
	 * 		The JGAAP event driver to be used.
	 * @param canonicizers
	 * 		The list of canonicizers to be applied on the parsed documents when applying this feature driver.
	 * @param cullers
	 * 		The list of event cullers to be applied when applying this feature driver.
	 * @param normalization
	 * 		The pair of normalization technique and factor to be applied on the event set generated by this feature driver. 
	 */
	public FeatureDriver(String name, boolean calcHist, EventDriver underlyEventDriver, List<Canonicizer> canonicizers,
			List<EventCuller> cullers, Pair<NormBaselineEnum,Double> normalization) {
		this(name,calcHist,underlyEventDriver,canonicizers,cullers);
		this.norm = normalization;
	}
		
	
	/* ==================
	 * adders and setters
	 * ==================
	 */
	
	/**
	 * Sets the name of the feature driver to the given one.
	 * @param name
	 * 		The name to be set.
	 */
	public void setDisplayName(String name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the description of the feature driver to the given one.
	 * @param description
	 * 		The description to be set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the underlying JGAAP event driver to the given one.
	 * @param underlyingEventDriver
	 * 		The event driver to be set.
	 */
	public void setUnderlyingEventDriver(EventDriver underlyingEventDriver) {
		this.underlyingEventDriver = underlyingEventDriver;
	}
	
	/**
	 * Sets whether to calculate histogram over the output event set or simply take the numeric value
	 * of the (only) event in that event set.
	 * @param calcHist
	 * 		Defines whether to calculate histogram over the output event set or simply take the numeric value
	 * 		of the (only) event in that event set.
	 */
	public void setCalcHist(boolean calcHist) {
		this.calcHist = calcHist;
	}
	
	/**
	 * Adds the given JGAAP canonicizer to the list of canonicizers.
	 * @param c
	 * 		The canonicizer to be added.
	 */
	public void addCanonicizer(Canonicizer c) {
		canonicizers.add(c);
	}

	/**
	 * Sets the list of JGAAP canonicizers to the given one.
	 * @param canonicizers
	 * 		The list of canonicizers to be set.
	 */
	public void setCanonicizers(List<Canonicizer> canonicizers) {
		this.canonicizers = canonicizers;
	}

	/**
	 * Adds the given JGAAP event culler to the list of event cullers.
	 * @param ec
	 * 		The event culler to be added.
	 */
	public void addEventCuller(EventCuller ec) {
		cullers.add(ec);
	}

	/**
	 * Sets the list of JGAAP event cullers to the given one.
	 * @param canonicizers
	 * 		The list of event cullers to be set.
	 */
	public void setCullers(List<EventCuller> cullers) {
		this.cullers = cullers;
	}

	/**
	 * Sets the normalization baseline to the given one.
	 * @param norm
	 * 		The normalization baseline to be set.
	 */
	public void setNormBaseline(NormBaselineEnum norm) {
		this.norm = new Pair<NormBaselineEnum,Double>(norm,(this.norm != null ? this.norm.getSecond() : null));
	}
	
	/**
	 * Sets the normalization factor to the given one.
	 * @param factor
	 * 		The normalization factor to be set.
	 */
	public void setNormFactor(double factor) {
		norm = new Pair<NormBaselineEnum,Double>((this.norm != null ? this.norm.getFirst() : null),factor);
	}
	
	/**
	 * Sets the normalization baseline and factor pair to the given one.
	 * @param normalization
	 * 		The normalization baseline and factor pair to be set.
	 */
	public void setNormalization(Pair<NormBaselineEnum, Double> normalization) {
		this.norm = normalization;
	}
	
	/* =======
	 * getters
	 * =======
	 */
	
	/**
	 * Returns the name of the feature driver.
	 * @return
	 * 		The name of the feature driver.
	 */
	public String displayName() {
		return name;
	}
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the description of the feature driver.
	 * @return
	 * 		The description of the feature driver.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns the underlying JGAAP event driver.
	 * @return
	 * 		The underlying event driver.
	 */
	public EventDriver getUnderlyingEventDriver() {
		return underlyingEventDriver;
	}
	
	
	/**
	 * Returns whether to calculate histogram over the output event set or simply take the numeric value
	 * of the (only) event in that event set.
	 * @return
	 * 		Whether to calculate histogram over the output event set or simply take the numeric value
	 * 		of the (only) event in that event set.
	 */
	public boolean isCalcHist() {
		return calcHist;
	}
	
	/**
	 * Returns the JGAAP canonicizer at the given index, or null if does not exist. 
	 * @param i
	 * 		The index of the desired canonicizer.
	 * @return
	 * 		The canonicizer at the given index, or null if does not exist.
	 */
	public Canonicizer canonicizerAt(int i) {
		try {
			return canonicizers.get(i);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Returns the list of JGAAP canonicizers.
	 * @return
	 * 		The list of canonicizers.
	 */
	public List<Canonicizer> getCanonicizers() {
		return canonicizers;
	}
	
	/**
	 * Returns the JGAAP event culler at the given index, or null if does not exist. 
	 * @param i
	 * 		The index of the desired event culler.
	 * @return
	 * 		The event culler at the given index, or null if does not exist.
	 */
	public EventCuller cullerAt(int i) {
		try {
			return cullers.get(i);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Returns the list of JGAAP event drivers.
	 * @return
	 * 		The list of event drivers.
	 */
	public List<EventCuller> getCullers() {
		return cullers;
	}
	
	/**
	 * Returns the normalization baseline.
	 * @return
	 * 		The normalization baseline.
	 */
	public NormBaselineEnum getNormBaseline() {
		return norm.getFirst();
	}
	
	/**
	 * Returns the normalization factor.
	 * @return
	 * 		The normalization factor.
	 */
	public Double getNormFactor() {
		return norm.getSecond() == null ? 1 : norm.getSecond();
	}
	
	/**
	 * Returns the pair of normalization baseline and factor.
	 * @return
	 * 		The pair of normalization baseline and factor.
	 */
	public Pair<NormBaselineEnum, Double> getNormalization() {
		return norm;
	}
	
	/**
	 * Returns the list of parameters of a given JGAAP EventDriver class.
	 * This method should correspond to GUI.FeatureWizardDriver.getConfigPanel() 
	 * @param className
	 * 		The name of the event driver class.
	 * @return
	 * 		The list of parameters of a given JGAAP EventDriver class.
	 */
	public static List<Pair<String,ParamTag>> getClassParams(String className) {
		List<Pair<String,ParamTag>> res = new ArrayList<Pair<String,ParamTag>>();
		
		// JGAAP canonicizers
		
		
		// JStylo canonicizers
		if (className.equals("edu.drexel.psal.jstylo.canonicizers.RemoveFirstNLines")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		
		// JGAAP event drivers
		else if (className.equals("com.jgaap.eventDrivers.BlackListEventDriver")) {
			res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
			res.add(new Pair<String,ParamTag>("filename",ParamTag.FILE_PATH));
		}
		else if (className.equals("com.jgaap.eventDrivers.WhiteListEventDriver")) {
			res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
			res.add(new Pair<String,ParamTag>("filename",ParamTag.FILE_PATH));
		}
		else if (className.equals("com.jgaap.eventDrivers.MNLetterWordEventDriver")) {
			res.add(new Pair<String,ParamTag>("M",ParamTag.INTEGER));
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
			//res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
		}
		else if (className.equals("com.jgaap.eventDrivers.CharacterNGramEventDriver")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("com.jgaap.eventDrivers.POSNGramEventDriver")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("com.jgaap.eventDrivers.RareWordsEventDriver")) {
			res.add(new Pair<String,ParamTag>("M",ParamTag.INTEGER));
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
			res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
		}
		else if (className.equals("com.jgaap.eventDrivers.SuffixEventDriver")) {
			res.add(new Pair<String,ParamTag>("length",ParamTag.INTEGER));
			res.add(new Pair<String,ParamTag>("minimumlength",ParamTag.INTEGER));
			res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
		}
		else if (className.equals("com.jgaap.eventDrivers.SyllableTransitionEventDriver")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("com.jgaap.eventDrivers.TruncatedEventDriver")) {
			res.add(new Pair<String,ParamTag>("length",ParamTag.INTEGER));
			res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
		}
		else if (className.equals("com.jgaap.eventDrivers.TumblingNGramEventDriver")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
			res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
		}
		else if (className.equals("com.jgaap.eventDrivers.VowelMNLetterWordEventDriver")) {
			res.add(new Pair<String,ParamTag>("M",ParamTag.INTEGER));
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("com.jgaap.eventDrivers.WordNGramEventDriver")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		
		// JStylo event drivers
		else if (className.equals("edu.drexel.psal.jstylo.eventDrivers.EventsCounterEventDriver")) {
			res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventDrivers.FastTagPOSNGramsEventDriver")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventDrivers.MaxentPOSNGramsEventDriver")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventDrivers.LetterNGramEventDriver")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventDrivers.ListEventDriver")) {
			res.add(new Pair<String,ParamTag>("sort",ParamTag.BOOLEAN));
			res.add(new Pair<String,ParamTag>("whiteList",ParamTag.BOOLEAN));
			res.add(new Pair<String,ParamTag>("keepLexiconInMem",ParamTag.BOOLEAN));
			res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
			res.add(new Pair<String,ParamTag>("filename",ParamTag.FILE_PATH));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventDrivers.ListRegexpEventDriver")) {
			//res.add(new Pair<String,ParamTag>("sort",ParamTag.BOOLEAN));
			res.add(new Pair<String,ParamTag>("whiteList",ParamTag.BOOLEAN));
			res.add(new Pair<String,ParamTag>("keepLexiconInMem",ParamTag.BOOLEAN));
			//res.add(new Pair<String,ParamTag>("underlyingEvents",ParamTag.CLASS_NAME));
			res.add(new Pair<String,ParamTag>("filename",ParamTag.FILE_PATH));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventDrivers.RegexpCounterEventDriver")) {
			res.add(new Pair<String,ParamTag>("regexp",ParamTag.STRING));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventDrivers.RegexpEventDriver")) {
			res.add(new Pair<String,ParamTag>("regexp",ParamTag.STRING));
		}
		
		
		// JGAAP cullers
		else if (className.equals("com.jgaap.eventCullers.LeastCommonEvents")) {
			res.add(new Pair<String,ParamTag>("numEvents",ParamTag.INTEGER));
		}
		else if (className.equals("com.jgaap.eventCullers.MostCommonEvents")) {
			res.add(new Pair<String,ParamTag>("numEvents",ParamTag.INTEGER));
		}
		
		// JStylo cullers
		else if (className.equals("edu.drexel.psal.jstylo.eventCullers.LeastCommonEventsExtended")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventCullers.MostCommonEventsExtended")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventCullers.MinAppearances")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		else if (className.equals("edu.drexel.psal.jstylo.eventCullers.MaxAppearances")) {
			res.add(new Pair<String,ParamTag>("N",ParamTag.INTEGER));
		}
		
		return res;
	}
	
	public enum ParamTag {
		FILE_PATH,
		CLASS_NAME,
		INTEGER,
		DOUBLE,
		BOOLEAN,
		STRING
	}
}
