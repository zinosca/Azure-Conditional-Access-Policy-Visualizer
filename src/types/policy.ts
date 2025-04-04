export interface ApplicationFilter {
  Mode: string | null;
  Rule: string | null;
}

export interface Applications {
  ApplicationFilter: ApplicationFilter;
  ExcludeApplications: string[];
  IncludeApplications: string[];
  IncludeAuthenticationContextClassReferences: string[];
  IncludeUserActions: string[];
}

export interface ClientApplications {
  ExcludeServicePrincipals: string[] | null;
  IncludeServicePrincipals: string[] | null;
  ServicePrincipalFilter: ApplicationFilter;
}

export interface Devices {
  DeviceFilter: ApplicationFilter;
}

export interface Locations {
  ExcludeLocations: string[];
  IncludeLocations: string[];
}

export interface Platforms {
  ExcludePlatforms: string[] | null;
  IncludePlatforms: string[] | null;
}

export interface ExternalTenants {
  "@odata.type"?: string;
  MembershipKind: string | null;
  members?: string[];
}

export interface GuestsOrExternalUsers {
  guestOrExternalUserTypes?: string;
  ExternalTenants?: ExternalTenants;
  GuestOrExternalUserTypes?: string | null;
  externalTenants?: ExternalTenants;
}

export interface Users {
  ExcludeGroups: string[];
  excludeGuestsOrExternalUsers?: GuestsOrExternalUsers;
  ExcludeRoles: string[];
  ExcludeUsers: string[];
  IncludeGroups: string[];
  IncludeGuestsOrExternalUsers?: GuestsOrExternalUsers;
  IncludeRoles: string[];
  IncludeUsers: string[];
}

export interface PolicyConditions {
  Applications: Applications;
  ClientAppTypes: string[];
  ClientApplications: ClientApplications;
  Devices: Devices;
  Locations: Locations;
  Platforms: Platforms;
  ServicePrincipalRiskLevels: string[];
  SignInRiskLevels: string[];
  UserRiskLevels: string[];
  Users: Users;
}

export interface AuthenticationStrength {
  AllowedCombinations: string[] | null;
  CombinationConfigurations: any[] | null;
  CreatedDateTime: string | null;
  Description: string | null;
  DisplayName: string | null;
  Id: string | null;
  ModifiedDateTime: string | null;
  PolicyType: string | null;
  RequirementsSatisfied: string | null;
}

export interface GrantControls {
  AuthenticationStrength: AuthenticationStrength;
  BuiltInControls: string[];
  CustomAuthenticationFactors: string[];
  Operator: "OR" | "AND";
  TermsOfUse: string[];
}

export interface SessionControls {
  ApplicationEnforcedRestrictions: {
    IsEnabled: boolean | null;
  };
  CloudAppSecurity: {
    CloudAppSecurityType: string | null;
    IsEnabled: boolean | null;
  };
  DisableResilienceDefaults: boolean | null;
  PersistentBrowser: {
    IsEnabled: boolean | null;
    Mode: string | null;
  };
  SignInFrequency: {
    AuthenticationType: string | null;
    FrequencyInterval: string | null;
    IsEnabled: boolean | null;
    Type: string | null;
    Value: number | null;
  };
}

export interface Policy {
  Name: string;
  DisplayName: string;
  State: "enabled" | "disabled" | "enabledForReportingButNotEnforced";
  Conditions: PolicyConditions;
  GrantControls: GrantControls;
  SessionControls: SessionControls;
}

export interface AccessTestInput {
  location: string;
  user: string;
  application: string;
  device: string;
  role: string;
} 